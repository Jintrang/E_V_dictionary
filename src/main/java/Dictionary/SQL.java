package Dictionary;

import java.sql.*;
import java.util.*;

import static Dictionary.Dictionary.Words;
//import static Dictionary.Dictionary.searchWord;
import static Dictionary.DictionaryManagement.dictionaryLookup;

public class SQL {

    /**
     * url: dataname
     */
    public String url = "jdbc:mysql://localhost:3306/edict";
    public String username = "root";
    public String password = "123123123";
    public static String table = "tbl_edict";
    public static Connection connection;
    public static List<String> wordsList = new ArrayList<>();
    // tạo 1 array mới để thêm cả từ và nghĩa
    //public static List<Word> list= new ArrayList<>();
    public static int num = 0;

    public SQL() throws SQLException {
        connect();
        getWords(getAllWord());
        wordList();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            num = getNumber();
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    /**
     * trả về tất cả các từ.
     */
    public ResultSet getAllWord() {
        ResultSet rs = null;
        String sqlCommand = "select * from " + table;
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return rs;
    }

    // thêm từ vào Dictionary.Words
    public void getWords(ResultSet rs) throws SQLException {
        while (rs.next()) {
            Dictionary.addWord(rs.getString(2), rs.getString(3));
        }
    }

    //thêm từ vào wordList
    public void wordList() throws SQLException {
        //connect();
        wordsList = new ArrayList<>();
        //list = new ArrayList<>();
        ResultSet rs = getAllWord();
        while (rs.next()) {
            wordsList.add(rs.getString(2));
            //list.add(new Word(rs.getString(2), rs.getString(3)));
        }
    }

    /**
     * xóa từ.
     */
    public void deleteWord(String word_target) {
        String sqlCommand = "delete from " + table + " where word = ?";
        PreparedStatement pst = null;
        int index = dictionaryLookup(word_target);
        Word word = new Word(Words.get(index).word_target,Words.get(index).word_explain);
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, word_target);
            if (pst.executeUpdate() > 0) {
                System.out.println("Đã xóa");
                num--;
                wordsList.remove(word_target);
                Words.remove(word);
            } else {
                System.out.println("Không có từ cần xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * thêm từ.
     */
    public static void insert(String newWord, String meaning) {
        String sqlCommand = "insert into " + table + " value(?, ?, ?)";
        PreparedStatement pst = null;
        wordsList.add(newWord);
        Word word  = new Word(newWord, meaning);
        Words.add(word);
        wordsList.add(newWord);
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setInt(1, ++num );
            pst.setString(2, newWord);
            pst.setString(3, meaning);
            if (pst.executeUpdate() > 0) {
                System.out.println("Thêm từ thành công: " + meaning);
            } else {
                System.out.println("Chưa thể thêm từ!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sửa từ.
     */
    public boolean fixWord(String word_target, String meaning) {
        String sqlCommand = "update " + table + " set detail = ? where word = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, meaning);
            pst.setString(2, word_target);
            if (pst.executeUpdate() > 0) {
                System.out.println("update success :" + pst.executeUpdate());
            } else {
                System.out.println("update error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (DictionaryManagement.fixWords(word_target, meaning));
    }

    public String showData(String word) throws SQLException {
        int tmp = dictionaryLookup(word);
        String res = Words.get(tmp).getWord_explain();
        return res;
    }
    private int getNumber() {
        int n = 0;
        ResultSet rs = null;
        String sqlCommand = "SELECT * FROM " + table + " ORDER BY idx DESC LIMIT 1";
        Statement st;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
            rs.next();
            n = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Select error");
            e.printStackTrace();
        }
        return n;

    }
}
