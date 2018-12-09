package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MohaDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "moha"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public MohaDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_query = "CREATE TABLE MOHA (_id INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT, ANSWER TEXT)";
        db.execSQL(sql_query); //execute this sql query
        ContentValues mohaValues = new ContentValues();
        insertQuestion(db,".中午下班，饥肠辘辘，此时你会选择去哪家快餐店饱腹一番?\nA.肯德基 B.麦当劳 C.华莱士 D.必胜客","C");//insert the first entry
        insertQuestion(db,"新闻界召开短跑大赛，哪里的记者铁定会夺取第一？\nA.西方记者 B.香港记者 C.大陆记者 D.日本记者","B");
        insertQuestion(db,".你觉得哪句俗语让你受益匪浅？\nA.闷声大发财 B.说曹操曹操到 C.失败乃成功之母 D.知之者不如好之者","A");
        insertQuestion(db,"下列四个关于“笑”成语你觉得哪个与众不同？\nA.开怀大笑 B.笑面老虎 C.谈笑风生 D.笑里藏刀 ","C");
        insertQuestion(db,"“好啊”用广东话怎么说？ \nA.豪啊 B.合啊 C.吼啊 D.呼啊","C");
        insertQuestion(db,"“比喻看势头或看别人的眼色行事，根据形势的变化而改变方向或态度”\n这是哪个成语/俗语的释义？\nA.见得风是得雨 B.见风使舵 C.墙头草随风倒 D.随机应变","A");
        insertQuestion(db,"年轻的你应该向谁请教一点“人生的经验”？\nA.父母 B.长者 C.老师 D.大哥","B");
        insertQuestion(db,"嘟嘟儿子在哈佛读书的学费从哪儿来？ （ ）\nA.嘟嘟的工资 B.嘟嘟夫人的律师所 C.全额奖学金 D.助学贷款","C");
        insertQuestion(db,"嘟嘟在任期间现在的资产状况？ （ ）\nA.300W B.500W C.800W D.没有任何的个人资产","D");
        insertQuestion(db,"长者的大学母校是：（ ）\nA.北京大学 B.上海交通大学 C.清华大学 D.北京人民大学\n","B");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertQuestion(SQLiteDatabase db, String question, String answer){
        //method to insert a moha question
        ContentValues mohaValues = new ContentValues();
        mohaValues.put("QUESTION",question);//insert question
        mohaValues.put("ANSWER",answer);//insert answer: such as "A", "B", "C", or "D"
        db.insert("MOHA",null,mohaValues);//insert a row
    }
}
