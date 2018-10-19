package lovesms.mywings.com.marathilovesms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TestAdapter {
    protected static final String TAG = "DataAdapter";
    private String dBname = "PP240417.db";
    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public TestAdapter(Context context) {
        this.mContext = context;
        this.mDbHelper = new DatabaseHelper(this.mContext, this.dBname);
    }

    public TestAdapter createDatabase() throws SQLException {
        try {
            this.mDbHelper.createDatabase();
            return this;
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + " UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
    }

    public TestAdapter open() throws SQLException {
        try {
            this.mDbHelper.openDataBase();
            this.mDbHelper.close();
            this.mDb = this.mDbHelper.getReadableDatabase();
            return this;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public void close() {
        this.mDbHelper.close();
    }

    public long insert(String[] values, String[] names, String tbl) {
        return this.mDb.insert(tbl, null, createContentValues(values, names));
    }

    public void insertValues(ContentValues cv, String tablename) {
        try {
            this.mDb.insert(tablename, null, cv);
            System.out.print("data Insert in table" + tablename);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.print("error while data Insert in table" + tablename);
        }
    }

    public ArrayList<String> GetTitle(String columnName, String tableName, int cat) {
        ArrayList<String> productName = new ArrayList();
        try {
            String sql = BuildConfig.FLAVOR;
            if (cat == 0) {
                sql = "select rtrim(" + columnName + ") as " + columnName + "  from " + tableName;
            } else {
                sql = "select rtrim(" + columnName + ") as " + columnName + "  from " + tableName + " where cat  =" + cat + " order by " + columnName;
            }
            Cursor c = this.mDb.rawQuery(sql, null);
            if (c == null) {
                productName.add(null);
            } else if (c.moveToFirst() && c.getCount() > 0) {
                do {
                    productName.add(c.getString(c.getColumnIndex(BuildConfig.FLAVOR + columnName + BuildConfig.FLAVOR)));
                } while (c.moveToNext());
                c.close();
            }
            return productName;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "sTitle  >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> GetTitleNames(String columnName, String tableName) {
        ArrayList<String> productName = new ArrayList();
        try {
            String sql = BuildConfig.FLAVOR;
            Cursor c = this.mDb.rawQuery("select rtrim(" + columnName + ") as " + columnName + "  from " + tableName + BuildConfig.FLAVOR, null);
            if (c == null) {
                productName.add(null);
            } else if (c.moveToFirst() && c.getCount() > 0) {
                do {
                    productName.add(c.getString(c.getColumnIndex(BuildConfig.FLAVOR + columnName + BuildConfig.FLAVOR)));
                } while (c.moveToNext());
                c.close();
            }
            return productName;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "sTitle  >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> GetNewTitleNames(String columnName, String tableName1, String tableName3, int cat) {
        ArrayList<String> productName = new ArrayList();
        try {
            String sql = BuildConfig.FLAVOR;
            if (cat == 1) {
                sql = "select rtrim(" + columnName + ") as " + columnName + " from " + tableName1 + " order by People_Id";
                String str = BuildConfig.FLAVOR;
            } else {
                sql = "select rtrim(" + columnName + ") as " + columnName + "  from " + tableName3 + " as M, tbl_people as P where M.People_Id = P.People_Id and Category_Id =" + cat + " order by M.Order_By ";
            }
            Cursor c = this.mDb.rawQuery(sql, null);
            if (c == null) {
                productName.add(null);
            } else if (c.moveToFirst() && c.getCount() > 0) {
                do {
                    productName.add(c.getString(c.getColumnIndex(BuildConfig.FLAVOR + columnName + BuildConfig.FLAVOR)));
                } while (c.moveToNext());
                c.close();
            }
            return productName;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "sTitle  >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> GetImages(String columnName, String tableName1, String tableName3, int cat) {
        ArrayList<String> productName = new ArrayList();
        try {
            String sql = BuildConfig.FLAVOR;
            String eData = BuildConfig.FLAVOR;
            String dData = BuildConfig.FLAVOR;
            if (cat == 1) {
                sql = "select rtrim(" + columnName + ") as " + columnName + " from " + tableName1 + " order by People_Id";
                String str = BuildConfig.FLAVOR;
            } else {
                sql = "select rtrim(" + columnName + ") as " + columnName + "  from " + tableName3 + " as M, tbl_people as P where M.People_Id = P.People_Id and Category_Id =" + cat + " order by M.Order_By ";
            }
            Cursor c = this.mDb.rawQuery(sql, null);
            if (c == null) {
                productName.add(null);
            } else if (c.moveToFirst() && c.getCount() > 0) {
                do {
                    productName.add(c.getString(c.getColumnIndex(BuildConfig.FLAVOR + columnName + BuildConfig.FLAVOR)));
                } while (c.moveToNext());
                c.close();
            }
            return productName;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "GetImages>>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<String> GetImagesList(String columnName, String tableName1, int cat) {
        ArrayList<String> productName = new ArrayList();
        try {
            String sql = BuildConfig.FLAVOR;
            String eData = BuildConfig.FLAVOR;
            Cursor c = this.mDb.rawQuery("select rtrim(" + columnName + ") as " + columnName + "  from " + tableName1 + " where category_id =" + cat + " order by sub_category_id", null);
            if (c == null) {
                productName.add(null);
            } else if (c.moveToFirst() && c.getCount() > 0) {
                do {
                    productName.add(c.getString(c.getColumnIndex(BuildConfig.FLAVOR + columnName + BuildConfig.FLAVOR)));
                } while (c.moveToNext());
                c.close();
            }
            return productName;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "GetImages>>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public int GetCatCount(int cat) {
        Cursor mCount = this.mDb.rawQuery("select count(nSrNo) from TblMalvaniSMS", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public int getAllCount(int cat) {
        Cursor mCount = this.mDb.rawQuery("select count(nSrNo) from TblMalvaniSMS where cat=" + cat + BuildConfig.FLAVOR, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public int getAllCountFirst() {
        Cursor mCount = this.mDb.rawQuery("select count(nSrNo) from TblMalvaniSMS", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public int getFirstCatCount() {
        Cursor mCount = this.mDb.rawQuery("select count(sSuvichar1) from TblMalvaniSMS", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public ArrayList<String> getBookMarked() {
        ArrayList<String> productName = new ArrayList();
        try {
            Cursor c = this.mDb.rawQuery("select sStatus  from Messages where IsFav=1", null);
            if (c == null) {
                productName.add(null);
            } else if (c.moveToFirst() && c.getCount() > 0) {
                do {
                    productName.add(c.getString(c.getColumnIndex("sStatus")));
                } while (c.moveToNext());
                c.close();
            }
            return productName;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "sStatus  >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public void updatevalues(String tablename, ContentValues cv, String whereColName, String whereColValue) {
        try {
            this.mDb.update(tablename, cv, whereColName + "='" + whereColValue + "'", null);
            System.out.println("Update Succesfull");
        } catch (Exception e) {
            e.toString();
            System.out.println("Fail To Update");
        }
    }

    public void updateWIthCondtion(String tbl, ContentValues cv, String rowId, String[] id) {
        try {
            this.mDb.update(tbl, cv, rowId, id);
            System.out.println("Update Succesfull");
        } catch (Exception e) {
            System.out.println("Fail To Update");
        }
    }

    public void updatevalues(ContentValues cv, String appno, String tablename) {
        this.mDb.update(tablename, cv, "app_formno = '" + appno + "'", null);
    }

    public void updateValues(ContentValues cv) {
        this.mDb.update("lnt_data_table", cv, null, null);
    }

    public String fetchvalue(String table, String wherevalue, String wherecolumn, String valuecolumn) {
        String fvalue = BuildConfig.FLAVOR;
        Cursor c = this.mDb.rawQuery("select " + valuecolumn + " from " + table + " where " + wherecolumn + "= '" + wherevalue + "'", null);
        if (c == null || c.getCount() <= 0) {
            fvalue = BuildConfig.FLAVOR;
        } else {
            c.moveToFirst();
            fvalue = c.getString(c.getColumnIndex(valuecolumn));
        }
        c.close();
        return fvalue;
    }

    public void updateDate(String table) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        try {
            ContentValues cv = new ContentValues();
            cv.put("LMD", dateFormat.format(cal.getTime()));
            Log.e("result", String.valueOf(this.mDb.update(table, cv, "rowid= (select max(rowid) from SchemeCode)", null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long upMaster(String upTable, String[] uColumn, String[] uValue, String uId, String colId) {
        return (long) Log.d("Updated :", String.valueOf((long) this.mDb.update(upTable, createContentValues(uValue, uColumn), colId + "='" + uId + "'", null)));
    }

    public int delMaster(String delTable, String vUnique, String cUnique) {
        int row = this.mDb.delete(delTable, cUnique + " = '" + vUnique + "'", null);
        Log.d("Rows Deleted :", String.valueOf(row));
        return row;
    }

    public void masterSync(String table, String[] columns, String[] values) {
        Log.e("syncResult", String.valueOf(this.mDb.insert(table, null, createContentValues(values, columns))));
    }

    public void updateM(String uTable, String[] uColumn, String[] uValue) {
        Log.e("syncUpdateFlag", String.valueOf((long) this.mDb.update(uTable, createContentValues(uValue, uColumn), null, null)));
    }

    private ContentValues createContentValues(String[] values, String[] names) {
        ContentValues values1 = new ContentValues();
        for (int i = 0; i < values.length; i++) {
            values1.put(names[i], values[i]);
        }
        return values1;
    }
}
