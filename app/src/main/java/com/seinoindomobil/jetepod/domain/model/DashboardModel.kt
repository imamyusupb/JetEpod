package com.seinoindomobil.jetepod.domain.model

import com.seinoindomobil.jetepod.R

class DashboardSlider1(
    val title: String? = null,
    val numberCount: Int? = null,
    val image: Int = 0
) {
    companion object {
        fun getData(): List<DashboardSlider1> {
            return listOf(
                DashboardSlider1("Sedang Diproses", 12000, R.drawable.img_sedang_diproses),
                DashboardSlider1("Proses Pengiriman", 1000, R.drawable.img_proses_pengiriman),
                DashboardSlider1("Sampai Tujuan", 920, R.drawable.img_sampai_tujuan)
            )
        }
    }
}
////////////////////////////////////////

data class FiturDashboardItem(
    val title: String? = null,
    val subTitle: String? = null,
    val buttonTitle: String? = null,
    val image: Int = 0,
)

val dummyFiturDashboardItem = listOf(
    FiturDashboardItem(
        "Buat Pesanan",
        "Membuat tiket booking baru",
        "Buat Tiket",
        R.drawable.ic_dashboard_fitu1
    ),
    FiturDashboardItem(
        "Terima Barang",
        "Silahkan gunakan fitur ini untuk terima barang",
        "Terima Barang",
        R.drawable.ic_dashboard_fitu2
    ),
    FiturDashboardItem(
        "Jadwalkan Kebutuhan",
        "Menjadwalkan kebutuhan unit Anda",
        "Buat Jadwal",
        R.drawable.ic_dashboard_fitu3
    ),
    FiturDashboardItem(
        "Lacak Pengiriman",
        "Gunakan fitur lacak untuk memantau pengiriman Anda",
        "Lacak Pengiriman",
        R.drawable.ic_dashboard_fitu4
    ),
)

/////////////////////////////////////////

data class BannerDashboardItem(
    val title: String? = null,
    val description: String? = null,
    val image: Int = 0
)

val dummyBannerDashboardItem = listOf(
    BannerDashboardItem(
        "Supported by PT Seino Indomobil Logistics",
        "Kirim barang kemana saja menggunakan aplikasi terintegrasi oleh PT Seino Indomobil Logistics",
        R.drawable.img_banner1
    ),
    BannerDashboardItem(
        "E-Pod Kini Semakin Mudah",
        "Akses dimanapun dan order kapanpun kami disini akan membantu anda dalam pengiriman",
        R.drawable.img_banner1
    ),
    BannerDashboardItem(
        "Update dan Lacak Barang Anda",
        "Selalu update informasi terkini mengenai barang kiriman Anda, kapanpun dan dimanapun",
        R.drawable.img_banner2
    ),
    BannerDashboardItem(
        "Pembuatan Order Semakin Mudah",
        "Semakin memudahkan customer dalam pembuatan order dengan instan dan simple",
        R.drawable.img_banner3
    ),
)

////////////////
data class KenalanDashboardItem(
    val title: String? = null,
    val description: String? = null,
    val image: Int = 0
)

val dummyKenalanDashboardItem = listOf(
    KenalanDashboardItem(
        "Buat Pesanan Pengiriman",
        "Semakin mudah untuk booking pengiriman, request pengiriman kapan saja dan dimana saja.",
        R.drawable.img_kenalan1
    ),
    KenalanDashboardItem(
        "Lacak Pengiriman Realtime",
        "Jangan khawatir dengan pesanan anda, E-Pod\n" +
                "dapat memantau proses pengiriman, request pengiriman kapan saja dan dimana saja.",
        R.drawable.img_kenalan2
    ),
    KenalanDashboardItem(
        "Jadwalkan Kebutuhan Unit Anda",
        "Anda dapat melakukan request unit langsung melalui aplikasi E-Pod",
        R.drawable.img_kenalan3
    ),
)

/////////////////

data class NotificationItem(
    val title: String? = null,
    val description: String? = null,
    val image: Int = 0,
    val date: String? = null
)

val dummyNotification = listOf(
    NotificationItem(
        "Ticket Booking Baru",
        "Anda dapat melihat ticket book baru yang telah dibuat",
        R.drawable.ic_notification_box,
        "17 Jun 2022, 12:48 PM"
    ),
    NotificationItem(
        "Absensi Mitra Driver",
        "Silahkan lakukan absensi kehadiran di aplikasi DMS\n" +
                "Mobile. ",
        R.drawable.ic_notification_absence,
        "17 Jun 2022, 12:48 PM"
    ),
    NotificationItem(
        "Driver Information",
        "Anda mendapatkan driver untuk pesanan #123",
        R.drawable.ic_notification_box_empty,
        "17 Jun 2022, 12:48 PM"
    ),
)