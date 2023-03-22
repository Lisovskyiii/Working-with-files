package com.company;
import java.io.*;
import java.util.Scanner;

public class Main {
    final static String path = "C:\\Users\\KIRILL\\IdeaProjects\\7 lab 5\\folder";

    public static void main(String[] args){
        try{
            deletePath();
            createPath();
            byte num ;
            do {
                MenuInfo();
                num = Check();
                switch (num) {
                    case 1: {Create(); break;}
                    case 2: {Rename(); break;}
                    case 3: {Write(); break;}
                    case 4: {Delete(); break;}
                    case 5: {Read(); break;}
                    case 0: {break;}
                    default: {System.out.println("Такого пункта нету :)"); break;}
                }
            } while (num != 0);
            deletePath();
        }
        catch(Exception e)
        {
        System.out.println("Wrong");
        main(args);
        }
    }
    static byte Check(){
        byte a;
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                a = in.nextByte();
                break;
            } catch (Exception ex) {
                System.out.println("Попробуйте еще раз");
            }
        }
        return a;
    }
    static void MenuInfo(){
        System.out.println("""

                1. Создать файл;
                2. Переименовать файл;
                3. Записать информацию в файл;
                4. Удалить файл;
                5. Прочитать файл;
                0. Выход
                """);
    }
    static void Create() throws Exception{
        String Name = InputName();
        File newFile = new File(path + Name);
        boolean result = newFile.createNewFile();
        if(result) {
            System.out.println("File is saved");
        }
    }
    static void Read() throws  Exception {
        String Name = getFileName();
        if(!Name.equals("0")) {
            System.out.println();
            File file = new File(path + Name);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
                while((str = br.readLine()) != null) {
                    System.out.println(str);
                }
        }
        else{System.out.println("Нету файлов");}
    }
    static void Rename(){
        String Name = getFileName();
        if(!Name.equals("0")) {
            String way = path + Name;
            File file = new File(way);
            Name = InputName();
            way = path + Name;
            File newName = new File(way);
            boolean renamed = file.renameTo(newName);
            if (renamed) {
                System.out.println("Файл был переименован");
            } else {
                System.out.println("Wrong");
            }
        }
        else{System.out.println("Нету файлов");}
    }
    static void Delete(){
        String Name = getFileName();
        if(!Name.equals("0")) {
            String way = path + Name;
            File file = new File(way);
            boolean result = file.delete();
            if (result) {
                System.out.println("Файл был удален");
            } else {
                System.out.println("Wrong");
            }
        }
        else{System.out.println("Нету файлов");}
    }
    static void createPath(){
        File file = new File(path);
        boolean result = file.mkdir();
        if (result) {
            System.out.println("Папка была создана");
        } else {
            System.out.println("Wrong");
        }
    }
    static String InputName(){
        String file = "";
        while (true) {
            try {
                System.out.print("Введите имя файла: ");
                Scanner in = new Scanner(System.in);
                file = in.nextLine();
                break;
            } catch (Exception ex) {
                System.out.println("Wrong");
            }
        }
        String result = "/"+file+".txt";
        return result;
    }
    static int inputNumber(){
        byte num;
        while (true) {
            try {
                System.out.print("Введите номер файла: ");
                Scanner in = new Scanner(System.in);
                num = in.nextByte();
                break;
            } catch (Exception ex) {
                System.out.println("Wrong");
            }
        }
        return num;
    }
    static String getFileName(){
        File file = new File(path);
        String[] files = file.list();
        byte numberOfFile;
        assert files != null;
        if(files.length != 0) {
            do {
                numberOfFile = (byte) inputNumber();
                if (numberOfFile < 1 || numberOfFile > files.length) {
                    System.out.println("Файла с таким номером нет");
                } else {
                    numberOfFile--;
                    break;
                }
            } while (true);
            String result="/"+files[numberOfFile];
            return result;
        }
        return "0";
    }
    static void Write() throws  Exception{
        String Name = getFileName();
        if(!Name.equals("0")){
            File file = new File(path + Name);
            FileWriter writer = new FileWriter(file, false);
            System.out.println("Введите текст для записи в файл (\"stop\")\n");
            String text = "";
                while(true) {
                    Scanner in = new Scanner(System.in);
                    text = in.nextLine();
                    if(text.equals("stop")) {
                        break;
                    }
                    else {
                        text += "\n";
                        writer.write(text);
                        writer.flush();
                    }
            }
        }
        else{System.out.println("Нету файлов");}
    }
    static void deletePath(){
        File file = new File(path);
        file.delete();
    }
}
