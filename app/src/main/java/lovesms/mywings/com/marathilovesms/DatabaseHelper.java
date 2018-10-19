package lovesms.mywings.com.marathilovesms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "PP240417.db";
    private static String DB_PATH = BuildConfig.FLAVOR;
    private static String FILE_NAME = "PP240417.zip";
    private static String TAG = "DataBaseHelper";
    private String DBText;//= this.myContext.getResources().getString(R.string.db_password);
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DatabaseHelper(Context context, String dbtext) {
        super(context, DB_NAME, null, 33);

        this.myContext = context;
        //DBText = this.myContext.getResources().getString(R.string.db_password);
        DBText = "spp@1720@pp2404";
        //DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        DB_PATH = "/data/data/" + "lovesms.mywings.com.marathilovesms" + "/databases/";
    }

    public void createDatabase() throws IOException {
        if (checkdatabase()) {
            getReadableDatabase();
            close();
            return;
        }
        getReadableDatabase();
        try {
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    private boolean checkdatabase() {
        return new File(DB_PATH + DB_NAME).exists();
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = this.myContext.getAssets().open(FILE_NAME);
        String outFileName = DB_PATH + FILE_NAME;
        String destination = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        while (true) {
            int length = myInput.read(buffer);
            if (length <= 0) {
                break;
            }
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(outFileName);
        } catch (ZipException e) {
            e.printStackTrace();
        }
        try {
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(this.DBText);
                zipFile.extractAll(DB_PATH);
            }
        } catch (ZipException e2) {
            e2.printStackTrace();
        }
    }

    public boolean openDataBase() throws SQLException {
        this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return this.myDataBase != null;
    }

    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
