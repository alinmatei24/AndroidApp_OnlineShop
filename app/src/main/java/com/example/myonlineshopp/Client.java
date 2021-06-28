package com.example.myonlineshopp;
import android.content.Intent;
import java.net.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread{
    private static Socket socket= null;
    private static InputStreamReader inputStreamReader  = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static boolean logIn=false;

    public static void connectToServer(){
        try{
            socket=new Socket( "169.254.233.98", 5050);
            System.out.println("Conected to the server\n");

        } catch (UnknownHostException e) {
            System.out.println("Nu m-am conectat\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Nu m-am conectat\n");
            e.printStackTrace();
        }
    }
    public  static boolean checkLogIn(String email, String password) throws InterruptedException, IOException {
        logIn=false;
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("Login "+email + " " + password);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);

            if (result[0].equals("True")) {
                logIn=true;
            } else logIn=false;
        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
        System.out.println("logIn:"+logIn+"\n");

        return logIn;
    }
    public static boolean createAccount(String name, String email, String password, String dateOfBirth, String address) {
        logIn = false;
        try {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(), "UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);


            final String[] result = new String[1];
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("CreateUser " + name + " " + email + " " + password + " " + dateOfBirth + " " + address);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] = bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket" + result[0]);

            if (result[0].equals("True")) {
                logIn = true;
            } else logIn = false;
        } catch (Exception e) {
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
        System.out.println("logIn:" + logIn + "\n");
        return logIn;
    }
    public static User getUser(String email){
        User nou=null;
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("getUser "+ email);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);
            //iau userul, il pun in nou
            String dePeServer=result[0];
            String[]produse=dePeServer.split("\\^");
            System.out.println(produse.length);
            nou=new User(produse[1],produse[2],produse[3],produse[4],produse[5]);

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }

        return nou;
    }
    public static Product getProduct(String name){
        Product nou=null;
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("getProduct "+ name);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);
            //iau userul, il pun in nou
            String dePeServer=result[0];
            String[]produse=dePeServer.split("\\^");
            System.out.println(produse.length);
            nou=new Product(produse[1],produse[2],produse[3],produse[4]);

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }

        return nou;
    }
    public static void addComanda(String curentUser, String products){
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("insertCommand "+ curentUser + " " + products);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
    }
    public static void updateUsername(String name,String email){
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("updateName "+ email + " " + name);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);
            //iau userul, il pun in nou

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
    }
    public static void updatePassword(String password,String email){
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("updatePassword "+ email + " " + password);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);
            //iau userul, il pun in nou

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
    }
    public static void updateAge(String age,String email){
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("updateAge "+ email + " " + age);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);
            //iau userul, il pun in nou

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
    }
    public static void updateAddress(String address,String email){
        try {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            final String[] result = new String[1];
            Thread t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("updateAddress "+ email + " " + address);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] =bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
            System.out.println("Socket"+result[0]);
            //iau userul, il pun in nou

        }catch(Exception e){
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
    }
    public static ArrayList<Product> getProducts() throws InterruptedException {
        ArrayList<Product> myList = new ArrayList<Product>();
        final String[] result = new String[1];
        try {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(), "UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);



            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("getProducts");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] = bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
        } catch (Exception e) {
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
        //din myString creez lista de produse
        //System.out.println(result[0]);
        String dePeServer=result[0];
        String[]produseString=dePeServer.split("&");
        //System.out.println("Acuma split, lungime: "+ produseString.length);
        //acuma creez lista cu useri
        // stiu ca am 4 campuri la fiecare produs asa ca impart nr de elemente la 4 si fac loop
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                for(String produs : produseString){
                    try {
                        Product produsNou = new Product(produs.split("asf")[0], produs.split("asf")[1], produs.split("asf")[2], produs.split("asf")[3]);
                        //System.out.println(produsNou.getName());
                        myList.add(produsNou);
                    }catch(Exception e){
                        System.out.println(produs);
                    }
                }
            }
        });
        t3.start();
        t3.join();
        return myList;
    }
    public static String[] getCommands(String email){
        final String[] result = new String[1];
        try {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    connectToServer();
                }
            });
            t1.start();
            t1.join();
            inputStreamReader = new InputStreamReader(socket.getInputStream(), "UTF-8");
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bufferedWriter.write("getCommands" + " " + email);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        //read
                        result[0] = bufferedReader.readLine();
                        bufferedReader.close();
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t2.join();
            socket.close();
        } catch (Exception e) {
            System.out.println("Nu am trimis mesaj\n");
            e.printStackTrace();
        }
        //din myString creez lista de produse pt istoric
        String dePeServer=result[0];
        String[]produse=dePeServer.split("asf")[1].split("&");
        System.out.println("Acuma split, lungime: "+ produse.length);
        return produse;
    }
}