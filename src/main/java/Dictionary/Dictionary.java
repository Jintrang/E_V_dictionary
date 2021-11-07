package Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    // map lưu trữ các từ Word để sắp xếp từ theo thứ tự từ a-z
    public static List<Word> Words = new ArrayList<>();

    public static void printAllWords() {
        int distant = 1;
        System.out.println("NO\t\t| ENGLISH\t| Vietnamese");
        for (Word s : Words) {
            System.out.println(distant + s.getWord());
            distant++;
        }
    }

    // chuyển từ đã nhập thành chữ thường và thêm vào Dictionary.Words nếu chưa có
    public static void addWord(String newWord, String mean) {
        Dictionary.Words.add(new Word(newWord, mean));
    }

    // tìm từ trong Words
    /*
    public static Word findWord(String wordLook) {
        if (!Words.containsKey(wordLook)) {
            System.out.println("Không có từ cần tìm!");
            return new Word();
        } else
            return new Word(wordLook, Words.get(wordLook));
    }*/


    public static void print() throws Exception {
        DictionaryManagement.insertFromFile();
        for (Word s : Words) {
            System.out.println(s.word_target);
        }
    }
}