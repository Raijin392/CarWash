package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.traffic.TrafficLayer
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.response.CarWash
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.FragmentMainMapBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.DataContainer
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.UserPreferences
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.carWash.GetCarWashesUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.CarWashes

class MainMapFragment : Fragment(R.layout.fragment_main_map), UserLocationObjectListener,
    CameraListener {

    private var binding: FragmentMainMapBinding? = null

    private lateinit var userPreferences: UserPreferences

    private val getCarWashesUseCase: GetCarWashesUseCase = DataContainer.getCarWashesUseCase
    private lateinit var carWashes: CarWashes

    private lateinit var mapView: MapView

    private lateinit var userLocationLayer: UserLocationLayer
    private lateinit var trafficLayer: TrafficLayer
    private var routeStartLocation = Point(0.0, 0.0)

    private var permissionLocation = false
    private var followUserLocation = false

    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            var allAreGranted = true
            for (b in result.values) {
                allAreGranted = allAreGranted && b
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                if (this@MainMapFragment.isResumed) {
                    activity?.finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferences = UserPreferences(view.context)
        binding = FragmentMainMapBinding.bind(view)
        binding?.run {
            mapView = mapViewYandex
        }

        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                onMapReady()
            }
        }

        checkPermission()

        userInterfaceLocation()

        userInterfaceTraffic()

    }

    private fun userInterfaceTraffic() {
        binding?.run {
            var jams = false
            btnTrafficJams.setOnClickListener {
                when (jams) {
                    false -> {
                        jams = true
                        trafficLayer.isTrafficVisible = true
                    }

                    true -> {
                        jams = false
                        trafficLayer.isTrafficVisible = false
                    }
                }
            }
        }
    }

    private fun userInterfaceLocation() {
        binding?.btnMyGeolocation?.setOnClickListener {
            if (permissionLocation) {
                cameraUserPosition()

                followUserLocation = true
            } else {
                checkPermission()
            }
        }
    }

    private fun checkPermission() {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED ||
            context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            onMapReady()
        } else {
            val appPerms = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
            activityResultLauncher.launch(appPerms)
        }
    }

    private fun onMapReady() {
        val mapKit = MapKitFactory.getInstance()

        trafficLayer = mapKit.createTrafficLayer(mapView.mapWindow)
        userLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = true
        userLocationLayer.setObjectListener(this)

        binding?.mapViewYandex?.map?.addCameraListener(this)

        cameraUserPosition()

        permissionLocation = true

        lifecycleScope.launch {
            async {
                try {
                    carWashes = getCarWashesUseCase()
                } catch (error: Throwable) {
                    Log.e("Error", error.toString())
                }
            }.await()

            for (carWash in carWashes.carWashes) {
                mapView.map.mapObjects.addPlacemark(
                    Point(carWash.latitude, carWash.longitude),
                    ImageProvider.fromResource(context, R.drawable.car_wash)
                ).addTapListener(object : MapObjectTapListener {
                    override fun onMapObjectTap(p0: MapObject, p1: Point): Boolean {
                        val bundle: Bundle = Bundle()
                        bundle.putLong("carWashId", carWash.id)
                        findNavController().navigate(R.id.bookingFragment, bundle)
                        return true
                    }

                })
            }
        }
    }

    private fun cameraUserPosition() {
        if (userLocationLayer.cameraPosition() != null) {
            routeStartLocation = userLocationLayer.cameraPosition()!!.target
            mapView.map.move(
                CameraPosition(
                    routeStartLocation,
                    16f,
                    0f,
                    0f),
                Animation(Animation.Type.SMOOTH, 1f), null
            )
        } else {
            mapView.map.move(CameraPosition(Point(0.0, 0.0), 16f, 0f, 0f))
        }
    }

    override fun onCameraPositionChanged(
        p0: Map, p1: CameraPosition, p2: CameraUpdateReason, p3: Boolean
    ) {
        if (p3) {
            if (followUserLocation) {
                setAnchor()
            }
        } else {
            if (!followUserLocation) {
                noAnchor()
            }
        }
    }

    private fun setAnchor() {
        userLocationLayer.setAnchor(
            PointF(
                (mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()
            ), PointF(
                (mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()
            )
        )
        followUserLocation = false
    }

    private fun noAnchor() {
        userLocationLayer.resetAnchor()

    }

    override fun onObjectAdded(userLocationView: UserLocationView) {
        setAnchor()

        val pinIcon = userLocationView.arrow.useCompositeIcon()
        pinIcon.setIcon(
            "icon",
            ImageProvider.fromResource(context, R.drawable.navigation_arrow),
            IconStyle()
                .setAnchor(PointF(0.5f, 1f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(0f)
        )
        userLocationView.accuracyCircle.fillColor = Color.TRANSPARENT
    }

    override fun onObjectRemoved(p0: UserLocationView) {}

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {}


    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val mapKitApiKey = "7e77980b-5983-4a5f-ab8c-31524ee3399d"
    }
}
