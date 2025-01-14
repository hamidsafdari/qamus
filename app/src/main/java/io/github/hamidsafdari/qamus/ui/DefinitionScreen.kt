package io.github.hamidsafdari.qamus.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.hamidsafdari.qamus.R
import io.github.hamidsafdari.qamus.db.entity.Entry

@Composable
fun DefinitionScreen(entry: Entry) {
    val vazirFontFamily = remember {
        FontFamily(
            Font(R.font.vazirmatn_ui_fd_light, FontWeight.Light),
            Font(R.font.vazirmatn_ui_fd_regular, FontWeight.Normal),
            Font(R.font.vazirmatn_ui_fd_extralight, FontWeight.Normal, FontStyle.Italic),
            Font(R.font.vazirmatn_ui_fd_medium, FontWeight.Medium),
            Font(R.font.vazirmatn_ui_fd_bold, FontWeight.Bold)
        )
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Text(
                text = entry.keyword!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 5.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = vazirFontFamily
            )
            Text(
                text = entry.definition!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp, 20.dp, 10.dp),
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                fontFamily = vazirFontFamily
            )
        }
    }
}

@Preview
@Composable
fun DefinitionScreenPreview() {
    DefinitionScreen(
        entry = Entry(
            1,
            "ابّ",
            "(بتشديد باء) چراگاه. در مفردات و نهايه گويد: چراگاهي كه براي چريدن و چيدن آماده است. در اقرب الموارد گفته: گياهان خودرو كه چهار پايان خورند. «فَأَنْبَتْنٰا فِيهٰا حَبًّا … وَ فٰاكِهَةً وَ أَبًّا» عبس: 31، كلام مجمع نيز نظير مفردات و نهايه است.بايد دانست مراد از آن در آيه، علفهاي خودرو است زيرا. كه «أبّ» مفعول «انبتنا» است و روئيدن در علفهاست نه در محلّ آنها و آنجا كه چراگاه معني شده محل بالتبع مراد است. اين كلمه در كلام اللّٰه مجيد فقط يكبار يافته است و اصل آن چنانكه اهل لغت گفته اند بمعني تهيّؤ و آمادگي است: «أَبّ للسير أَبّا: تهيّأ له» در نهايه گفته: در حديث قسّ بن ساعده هست: «فجعل يرتع أَبّا» . يكي از محققين پس از نقل اقوال علماء در معني «ابّ» احتمال داده كه واو در «وَ أَبًّا» جزء كلمه است و آن بي تشديد ميباشد، آيه: «وَ فٰاكِهَةً وَ أَبًّا» مركب است از دو لفظ «فٰاكِهَةً» بمعني ميوه و «وأبا» بمعني درشت و شاداب و گوناگون عليهذا بقول ايشان واو عاطفه نيست، و «وأب» بر وزن دهر صفت «فاكهة» است. و آنگاه احتمال خويش را نزديك بيقين دانسته است. روح سخن ايشان در اين احتمال آنست كه اهل لغت و تفسير معناي صريحي و روايت صحيحي در بارۀ اين كلمه نگفته و نقل نكرده اند. ولي براي «وأب» معناي روشني است، ايضا ميگويد: علماي لغت «ابّ» را دخيل و غير عربي گفته اند. (ديوان دين ص 106- 124). نگارنده پس از تعمق و دقت، تحقيق ايشان را مورد قبول ندانستم زيرا اولا: هيچ يك از قرّاء باء «ابّ» را بي تشديد نخوانده است.ثانيا: در آنصورت تناسب و معني آيات درست نخواهد بود كه آيات بدين قرارند: (فَأَنْبَتْنٰا فِيهٰا حَبًّا. وَ عِنَباً وَ قَضْباً. وَ زَيْتُوناً وَ نَخْلًا. وَ حَدٰائِقَ غُلْباً. وَ فٰاكِهَةً وَ أَبًّا. مَتٰاعاً لَكُمْ وَ لِأَنْعٰامِكُمْ».در اين آيات چنانكه مي بينيم فرموده: از زمين براي شما دانه، انگور، تره، زيتون، درخت خرما باغهاي انبوه يا درختان بزرگ، ميوه و چراگاه رويانديم. پس از آن ميفرمايد: اينها متاعند براي شما و چهار پايان شما.معلوم است كه انگور، تره و غيره معمولا خوراك انسان است، اگر «أَبًّا» را وأب خوانده و وصف فاكهه بدانيم و درشت معني كنيم بجملۀ «متاعا … لانعامكم» محلّي باقي نخواهد ماند زيرا مذكورات ما قبل، همه «مَتٰاعاً لَكُمْ» اند و اينكه قضب بمعني يونجه است و در صحاح گويد: آن اسفست (اسپست: يونجه) فارسي است مطلب تمام نميشود زيرا ظاهرا مراد از آن در آيه ترۀ خوردني است و بمناسبت آنكه پشت سر هم چيده ميشود قضب گفته شده وانگهي اگر يونجه باشد براي انعام كافي نيست.اما اگر «ابّ» را چراگاه بگيريم مطلب تمام خواهد بود.ثالثا: وصف فاكهه در آيات ديگر مؤنث آمده مثل: «بِفٰاكِهَةٍ كَثِيرَةٍ» زخرف: 73، واقعه: 32، ص: 51، لازم بود در آيۀ ما نحن فيه گفته شود: «فاكهة وأبة» تا صفت با موصوف در تأنيث موافق باشد، و لفظ وأب از صفات مشترك نيست تا مذكر و مؤنث در آن يكسان باشد و در لغت آنگاه كه موصوفش مؤنث باشد آمده: «قدر وأبة» و نيز آمده: «اناء وأب» (اقرب الموارد).وانگهي وأب بمعني ضخيم و واسع، وصف ميوه استعمال نشده بلكه وصف كاسه، گودال و نظير آنها آمده است. نويسندۀ فوق الذكر پس از توجّه باين نكته استعمال آنرا در ميوۀ درشت معناي مولّد (تازه) گرفته است تا اشكالي وارد نشود.در خاتمه لازم است بدانيم: أبّ از اوّل پر ماجري است. شيخ مفيد رحمه اللّٰه در ارشاد نقل ميكند: از ابو بكر راجع بلفظ ابّ سئوال شد در جواب گفت: فاكهه را مي شناسيم امّا ابّ خدا بآن داناتر است. اين سخن بامير المؤمنين عليه السّلام رسيد فرمود: سبحان اللّٰه آيا ندانسته كه ابّ علف و چراگاه است و خدا در «فٰاكِهَةً وَ أَبًّا» نعمتهاي خود را كه بر خلق و چهار پايان داده مي شمارد؟! زمخشري در كشاف ذيل آيۀ فوق قسمتي از روايت را نقل كرده و نظير آنرا از عمر نقل ميكند و در مقام اعتذار ميگويد: همت آنها مصروف بعمل بود نه بدانستن آنچه مورد عمل نبود.علّامه اميني در الغدير ج 7 ص 103 و ج 6 ص 100 قول ابن حجر شارح صحيح بخاري را نقل كرده كه گفته: بقولي ابّ دخيل است و عربي نيست. مؤيّد اين قول خفاء معني آن بر شيخين است. آنگاه فرموده: احدي از اهل لغت بدخيل بودن آن اشاره نكرده اند. نگارنده نيز در صحاح و قاموس و اقرب و نظير آنها، نديدم كه بدخيل بودن آن اشاره اي شده باشد."
        )
    )
}
