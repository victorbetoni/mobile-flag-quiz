package com.victorbetoni.flagquiz

import com.victorbetoni.flagquiz.data.Flag

class FlagRegistry {
    companion object {
        public val flags: Array<Flag> = arrayOf(
            Flag("Angola", R.drawable.ao),
            Flag("Argentina", R.drawable.ar),
            Flag("Holanda", R.drawable.bq),
            Flag("Brasil", R.drawable.br),
            Flag("Guatemala", R.drawable.gt),
            Flag("Chile", R.drawable.cl),
            Flag("China", R.drawable.cn),
            Flag("Marrocos", R.drawable.ma),
            Flag("Cuba", R.drawable.cu),
            Flag("Filipinas", R.drawable.ph),
            Flag("Jamaica", R.drawable.jm),
            Flag("Portugal", R.drawable.pt),
            Flag("Egito", R.drawable.eg),
            Flag("Estados Unidos", R.drawable.um),
        )
    }
}