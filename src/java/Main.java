import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *@author 'KAshirin Constantine'
 * @version 0.01
 *
 * Зато работает
 *
 */
public class Main {
    public static void main(String[] args) {

        //Записываем урл
        Scanner in = new Scanner(System.in);
        String query = in.nextLine();

        //Создаемм коннект
        HttpURLConnection connection = null;
        try {
            connection =(HttpURLConnection) new URL(query).openConnection();//открываем коннект

            connection.setRequestMethod("GET");//посылаем запрос

            connection.connect();//коннектимся

            StringBuilder stringBuilder = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                //System.out.println(stringBuilder.toString());

                System.out.println("-------------------------------------------------------------------");

                StringTokenizer tokenizer = new StringTokenizer(stringBuilder.toString(), "\t\n\r,. <>/'=-_;:"); //мама слепи мне снежку

                System.out.println(tokenizer.countTokens());
                /*while (tokenizer.hasMoreElements()){
                    System.out.println(tokenizer.nextElement());
                    //System.out.println(tokenizer.countTokens());

                }*/

            }
            else {
                System.out.println("Fail: " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            System.out.println("ex" + ex);
        }
        //Закрываем коннект
        finally {
            if (connection != null){
                connection.disconnect();
            }

        }
    }
}
