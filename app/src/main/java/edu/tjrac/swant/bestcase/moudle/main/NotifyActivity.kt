package edu.tjrac.swant.bestcase.moudle.main

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.widget.ImageView
import android.widget.TextView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.BaseActivity
import edu.tjrac.swant.bestcase.util.utils.GuiUtils
import kotterknife.bindView

/**
 * Created by wpc on 2017/4/27.
 */
class NotifyActivity : BaseActivity() {

    val mFabNotify: FloatingActionButton by bindView(R.id.fab_notify)
    val mTvContainerNotify: TextView by bindView(R.id.tv_container_notify)
    val mIvCloseNotify: ImageView by bindView(R.id.iv_close_notify)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation()
            setupExitAnimation()
        } else {
            initViews()
        }
        mIvCloseNotify.setOnClickListener {
            onBackPressed()
        }
    }

    // 退出事件
    override fun onBackPressed() {
        GuiUtils.animateRevealHide(this, mTvContainerNotify, mFabNotify.width / 2, R.color.accent_teal,
                object : GuiUtils.OnRevealAnimationListener {
                    override fun onRevealHide() {
                        defaultBackPressed()
                    }

                    override fun onRevealShow() {

                    }
                })
    }

    // 入场动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupEnterAnimation() {
        val transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {

            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
    }

    // 退出动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupExitAnimation() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 300
    }

    // 动画展示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animateRevealShow() {
        GuiUtils.animateRevealShow(
                this, mTvContainerNotify,
                mFabNotify.width / 2, R.color.accent_teal,
                object : GuiUtils.OnRevealAnimationListener {
                    override fun onRevealHide() {

                    }

                    override fun onRevealShow() {
                        initViews()
                    }
                })
    }

    // 默认回退
    private fun defaultBackPressed() {
        super.onBackPressed()
    }

    private fun initViews() {}

}
