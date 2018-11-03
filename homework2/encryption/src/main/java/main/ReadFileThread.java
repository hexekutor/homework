package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

class ReadFileThread implements Runnable
{
    private InputStream stream;
    private BlockingQueue<User> queue;
    ReadFileThread(InputStream stream, BlockingQueue<User> queue)
    {
        this.stream = stream;
        this.queue = queue;
    }

    public void run()
    {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int intSymbol;
            char symbol;
            String password = null;
            while((intSymbol =  stream.read()) != -1){
                symbol = (char)intSymbol;
                if(symbol != '|'){
                    stringBuilder.append(symbol);
                }else{
                    if(password == null){
                        password = stringBuilder.toString();
                    }else {
                        queue.put(new User(password, stringBuilder.toString()));
                        AmountOfUsers.setAmountOfUsers(AmountOfUsers.getAmountOfUsers() + 1);
                        password = null;
                    }
                    stringBuilder.setLength(0);
                }
            }
            stream.close();

            System.out.println("readed file with users");
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}