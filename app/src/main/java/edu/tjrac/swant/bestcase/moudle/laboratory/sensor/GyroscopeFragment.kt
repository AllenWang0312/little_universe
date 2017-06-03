package edu.tjrac.swant.bestcase.moudle.laboratory.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView

import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.util.utils.L
import edu.tjrac.swant.bestcase.widget.OscilloscopeSurfaceView

/**
 * Created by wpc on 2017/4/22.
 */

class GyroscopeFragment : Fragment() {

    internal var mContext: Context? = null
    val mTvXGyro: TextView by bindView(R.id.tv_x_gyro)
    val mTvYGyro: TextView by bindView(R.id.tv_y_gyro)
    val mTvZGyro: TextView by bindView(R.id.tv_z_gyro)
    var sensorManager: SensorManager? = null
    val mOsciForX: OscilloscopeSurfaceView by bindView(R.id.Osci_for_x)
    private val mSensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            L.i("values.length" + event.values.size)
            mTvXGyro.text = event.values[0].toString()
            mOsciForX.addValue(event.values[0].toDouble())
            mTvYGyro.text = event.values[1].toString()
            mTvZGyro.text = event.values[2].toString()
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            L.i("onAccuracyChanged" + accuracy + "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = activity
        val v = inflater!!.inflate(R.layout.fragment_gyroscope, container, false)
        sensorManager = mContext!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscope = sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager!!.registerListener(mSensorListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)

        return v
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sensorManager!!.unregisterListener(mSensorListener)
    }
}
