package com.clearpole.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yos.code.Yu2Java
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val iApp = Yu2Java(this)
        findViewById<Button>(R.id.bfyp).setOnClickListener {
              val mp = MediaPlayer()
              mp.setDataSource("/storage/emulated/0/等不来花开.mp3")
              mp.prepare()
              mp.start()
        }
        Thread {
        val lyric = """
            [00:02.01]等不来花开 - pro
            [00:04.02]词：不要再胖了
            [00:06.03]词：不要再胖了
            [00:08.04]编曲：不要再胖了/果冻
            [00:10.05]混音：阿良
            [00:12.06]策划：秦耍耍
            [00:14.07]制作人：舒心
            [00:16.08]监制：秦耍耍
            [00:16.08]联合宣发：河南网益文化
            [00:16.08]OP：瑞辰文化
            [00:16.08]【未经著作权人许可，不得翻唱、翻录或使用】
            [00:18.15]爱就像风筝断线飘走的无奈
            [00:22.14]忘记爱的漂流瓶遗留在大海
            [00:26.13]我期待雨过之后有彩色的云彩
            [00:30.18]就像我等你回来
            [00:33.15]我们的爱 就像秋叶等不到花开
            [00:37.20]我们的爱 就像风中漂浮的尘埃
            [00:41.19]我们的爱 我在结局后忽然明白
            [00:45.15]我们的爱 原本只是意外
            [00:49.86]爱你没有错对
            [00:50.97]怪我没有学会
            [00:51.99]怎么才能把你忘记释怀
            [00:53.91]相爱是个误会
            [00:54.96]分开也无所谓
            [00:55.95]大不了就当我还是小孩
            [00:57.93]不好不坏反正是宿醉
            [00:59.94]不恨不爱反正是暧昧
            [01:01.92]不好不坏也不过狼狈
            [01:03.93]不恨不爱只剩下酒杯
            [01:22.17]爱就像风筝断线飘走的无奈
            [01:26.13]忘记爱的漂流瓶遗留在大海
            [01:30.15]我期待雨过之后有彩色的云彩
            [01:34.20]就像我等你回来
            [01:37.14]我们的爱 就像秋叶等不到花开
            [01:41.25]我们的爱 就像风中漂浮的尘埃
            [01:45.42]我们的爱 我在结局后忽然明白
            [01:49.17]我们的爱 原本只是意外
            [01:53.88]爱你没有错对
            [01:54.96]怪我没有学会
            [01:55.98]怎么才能把你忘记释怀
            [01:57.87]相爱是个误会
            [01:59.01]分开也无所谓
            [01:59.97]大不了就当我还是小孩
            [02:01.95]不好不坏反正是宿醉
            [02:03.93]不恨不爱反正是暧昧
            [02:05.94]不好不坏也不过狼狈
            [02:07.92]不恨不爱只剩下酒杯
            [02:09.33]我们的爱 就像秋叶等不到花开
            [02:13.26]我们的爱 就像风中漂浮的尘埃
            [02:17.16]我们的爱 我在结局后忽然明白
            [02:21.15]我们的爱 原本只是意外
            """.trimIndent()
            val lyricList: ArrayList<LyricAdapter> = ArrayList()
            val lycArray = iApp.sl(lyric, "\n")
            for ((id, thisLine) in lycArray.withIndex()) {
                val thisLineTime = iApp.sj(thisLine, "[", "]")
                val thisLineLyric = iApp.sj(thisLine, "]", null)
                val adapter = LyricAdapter(thisLineTime, thisLineLyric ,id)
                lyricList.add(adapter)
            }
            runOnUiThread {
                val listView = findViewById<RecyclerView>(R.id.lyricView)
                listView.adapter =  RecyclerAdapter(lyricList)
                listView.layoutManager = LinearLayoutManager(this)
                findViewById<Button>(R.id.bfyp_tz).setOnClickListener{
                    listView.smoothScrollToPosition(20)
                }
            }
        }.start()
    }

    fun getNoMoreThanTwoDigits(number: Double): String {
        val format = DecimalFormat("0.##")
        //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
        format.roundingMode = RoundingMode.FLOOR
        return format.format(number)
    }
}

