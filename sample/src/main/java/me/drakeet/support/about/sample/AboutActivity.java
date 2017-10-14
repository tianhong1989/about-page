package me.drakeet.support.about.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.Items;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Card;
import me.drakeet.support.about.Category;
import me.drakeet.support.about.Contributor;
import me.drakeet.support.about.ImageLoader;
import me.drakeet.support.about.License;
import me.drakeet.support.about.Line;
import me.drakeet.support.about.Recommended;
import me.drakeet.support.about.provided.PicassoImageLoader;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;

/**
 * @author drakeet
 */
@SuppressLint("SetTextI18n")
@SuppressWarnings("SpellCheckingInspection")
public class AboutActivity extends AbsAboutActivity {

    @NonNull @Override
    protected ImageLoader provideImageLoader() {
        return new PicassoImageLoader();
    }


    @Override
    protected void onCreateHeader(@NonNull ImageView icon, @NonNull TextView slogan, @NonNull TextView version) {
        icon.setImageResource(R.mipmap.ic_launcher);
        slogan.setText("About Page By drakeet");
        version.setText("v" + BuildConfig.VERSION_NAME);
    }


    @Override
    protected void onItemsCreated(@NonNull Items items) {
        items.add(new Category("介绍与帮助"));
        items.add(new Card(getString(R.string.card_content)));

        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer", "http://weibo.com/drak11t"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));

        items.add(new Category("应用推荐"));
        items.add(new Recommended(
            0, "纯纯写作",
            "https://storage.recommend.wetolink.com/storage/app_recommend/images/YBMHN6SRpZeF0VHbPZWZGWJ2GyB6uaPx.png",
            "com.drakeet.purewriter",
            "这是一个快速的纯文本编辑器，我们希望写作能够回到原本的样子：纯粹、有安全感、随时、绝对不丢失内容、具备良好的写作体验。",
            "https://www.coolapk.com/apk/com.drakeet.purewriter",
            "2017-10-09 16:46:57",
            "2017-10-09 16:46:57", null, 2.93)
        );

        items.add(new Category("Open Source Licenses"));
        items.add(new License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"));
        items.add(new Line());
        items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        MenuItem dayNight = menu.findItem(R.id.menu_night_mode);
        dayNight.setChecked(AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_night_mode) {
            menuItem.setChecked(!menuItem.isChecked());
            if (menuItem.isChecked()) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
            }
            getDelegate().applyDayNight();
        }
        return true;
    }


    public void onClickShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_content));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }
}