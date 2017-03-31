package cn.base.io;

import java.nio.channels.Selector;
import java.lang.RuntimeException;
import java.lang.Thread;
class TestSelector {
    private static final int MAXSIZE=5;
    public static void main( String argc[] ) {
        Selector [] sels = new Selector[ MAXSIZE];
 
            try{
                for( int i = 0 ;i< MAXSIZE ;++i ) {
                    sels[i] = Selector.open();
                    //sels[i].close();
                }
                Thread.sleep(30000);
//                sels[1].close();
            }catch( Exception ex ){
                throw new RuntimeException( ex );
            }
    }
}