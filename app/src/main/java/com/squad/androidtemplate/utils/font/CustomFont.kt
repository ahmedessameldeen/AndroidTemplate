package com.squad.androidtemplate.utils.font

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import com.squad.androidtemplate.MyApp
import com.squad.androidtemplate.R

class CustomFont {
    companion object {

        private var lang: String? = "en"

        fun init(textView: TextView, context: Context, attrs: AttributeSet?) {
            lang = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales[0].language
            } else {
                context.resources.configuration.locale.language
            }
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.TextFonts,
                0, 0
            )
            try {
                val mFontStyle = a.getInteger(R.styleable.TextFonts_textStyleFont, 0)
                when (mFontStyle) {
                    FontFamily.LATO_B_NOTO_SANS_AR_B -> {
                        if (lang.equals("en")) {
                            textView.typeface = MyApp.latoBold
                        } else if (lang.equals("ar")) {
                            textView.typeface = MyApp.notoSansArBold
                        }
                    }
                    FontFamily.LATO_B_I_NOTO_SANS_AR_B -> {
                        if (lang.equals("en")) {
                            textView.typeface = MyApp.latoItalic
                        } else if (lang.equals("ar")) {
                            textView.typeface = MyApp.notoSansArBold
                        }

                    }
                    FontFamily.LATO_I_NOTO_AR_REG -> {
                        if (lang.equals("en")) {
                            textView.typeface = MyApp.latoItalic
                        } else if (lang.equals("ar")) {
                            textView.typeface = MyApp.notoSansArRegular
                        }

                    }
                    FontFamily.LATO_REG_NOTO_SANS_AR_REG -> {
                        if (lang.equals("en")) {
                            textView.typeface = MyApp.latoRegular
                        } else if (lang.equals("ar")) {
                            textView.typeface = MyApp.notoSansArRegular
                        }
                    }
                    FontFamily.OPTIMA_B_GE_DINAR_ONE_MEDIUM -> {
                        if (lang.equals("en")) {
                            textView.typeface = MyApp.optimaBold
                        } else if (lang.equals("ar")) {
                            textView.typeface = MyApp.geDinarOneMedium
                        }
                    }
                    FontFamily.OPTIMA_REG_GE_DINAR_ONE_MEDIUM -> {
                        if (lang.equals("en")) {
                            textView.typeface = MyApp.optimaRegular
                        } else if (lang.equals("ar")) {
                            textView.typeface = MyApp.geDinarOneMedium
                        }
                    }
                    else -> textView.typeface = MyApp.optimaRegular
                }
            } finally {
                a.recycle()
            }
        }
    }
}


