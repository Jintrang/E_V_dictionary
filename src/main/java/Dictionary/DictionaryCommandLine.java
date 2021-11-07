package Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Dictionary.Dictionary.Words;

public class DictionaryCommandLine {
    /*
     * in tất cả các từ trong Dictionary.Words.
     */
    public static void showAllWords() {
        Dictionary.printAllWords();
    }

    /*
     * gọi hàm DictionaryManagement.insertFromCommandline() và hàm showAllWords().
     * (thêm từ và in ra những từ có trong Dictionary.Words)
     */
    public static void dictionaryBasic() throws Exception{
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    /*
     * gọi hàm DictionaryManagement.insertFromFile() và hàm showAllWords() và DictionaryManagement.dictionaryLookup().
     * (thêm từ từ file và in ra những từ có trong Dictionary.Words và tìm kiếm từ)
     */
    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        showAllWords();
//        DictionaryManagement.dictionaryLookup();
    }

    /*
     * tìm kiếm từ hoặc 1 phần từ
     */
    public static List dictionarySearcher() {
        Scanner sc = new Scanner(System.in);

        List<String> listWord = new ArrayList<>();
        System.out.println("Nhap tu can tim: ");
        String wordLook = "";
        wordLook = sc.nextLine();
        for (Word s : Words) {
            String keyWord = s.word_target;
            if (keyWord.contains(wordLook)) {
                listWord.add(s.word_target);
                System.out.print(s.word_target + ", " );
            }
        }
        return listWord;
    }


    public static void main(String[] args) throws Exception {
        Dictionary.print();
    }
}