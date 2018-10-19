package lovesms.mywings.com.marathilovesms;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import net.lingala.zip4j.util.InternalZipConstants;

public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<String> ArrContent;
    ArrayList<String> ArrTitle;
    ArrayList<String> ImageNameList;
    Activity act;
    int cat_id;
    CheckBox chkFav;
    Button click;
    int currentPage;
    String[] demoArr;
    ImageView imageView;
    View layout;
    int no_of_size;
    public String strSelectedContent;
    TestAdapter tst_adapter;// = new TestAdapter(this.act);
    TextView txt_content;
    TextView txt_idicator;
    TextView txt_title;

    public ViewPagerAdapter(MainActivity mainActivity, int noofsize, int cat) {
        this.no_of_size = noofsize;
        this.cat_id = cat;
        this.act = mainActivity;
        tst_adapter = new TestAdapter(this.act);
        this.tst_adapter.createDatabase();
        this.tst_adapter.open();
        this.ArrContent = new ArrayList();
        this.ArrTitle = new ArrayList();
        this.ArrContent = this.tst_adapter.GetTitle(Constants.col2, Constants.tbl1, this.cat_id);
        this.no_of_size = this.ArrContent.size();
        String s = BuildConfig.FLAVOR;
    }

    public int getCount() {
        return this.no_of_size;
    }

    public Object instantiateItem(View container, int position) {
        LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.currentPage = position;
        this.layout = inflater.inflate(R.layout.main_page, null);
        int pagenumberTxt = position + 1;
        this.txt_content = (TextView) this.layout.findViewById(R.id.textMainContent);
        this.txt_content.setText((CharSequence) this.ArrContent.get(position));
        String p = (String) this.ArrContent.get(position);
        this.txt_title = (TextView) this.layout.findViewById(R.id.textTitle);
        this.strSelectedContent = this.txt_content.getText().toString();
        this.txt_idicator = (TextView) this.layout.findViewById(R.id.textNo);
        this.txt_idicator.setText((position + 1) + InternalZipConstants.ZIP_FILE_SEPARATOR + this.ArrContent.size());
        ((ViewPager) container).addView(this.layout, 0);
        return this.layout;
    }

    public int GetImageResourceID(String imageName) {
        try {
            return this.act.getResources().getIdentifier(imageName, "drawable", this.act.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String GetSelectedContent(int posn) {
        return (String) this.ArrContent.get(posn);
    }

    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    public Parcelable saveState() {
        return null;
    }
}
