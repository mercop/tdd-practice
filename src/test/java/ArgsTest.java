import com.tencent.jace.practice.Args;
import com.tencent.jace.practice.UnsupportError;
import org.junit.Ignore;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;

public class ArgsTest {
    @org.junit.Test
    @Ignore
    public void test0() {
        Args args = new Args("l:bool,d:int,f:string", "-d 8080");
        assertEquals(args.getValue("l"), FALSE);
        assertEquals(args.getValue("d"), new Integer(8080));
        assertEquals(args.getValue("f"), ".");
        assertEquals(args.getValue("x"), new UnsupportError("Unspport Error"));
    }


    @org.junit.Test
    @Ignore
    public void test1() {
        Args args = new Args("l:bool,d:int,f:string", "-l -d 8010 -f /usr/local");
        assertEquals(args.getValue("l"), FALSE);
        assertEquals(args.getValue("d"), new Integer(8080));
        assertEquals(args.getValue("f"), "/usr/local");
    }

    @Test
    @Ignore
    public void test_2() {
        Args args = new Args("l:bool,d:int,f:string,s:string", "-l true -d -9 -f /usr/local -s");
        assertEquals(args.getValue("l"), TRUE);
        assertEquals(args.getValue("d"), new Integer(-9));
        assertEquals(args.getValue("f"), "/usr/local");
        assertEquals(args.getValue("s"), null);
    }

    @Test
    @Ignore
    public void test_3() {
        Args args = new Args("l:bool,d:int,f:string,s:string", "-l TRue -d 001 -f /usr/ local -s");
        assertEquals(args.getValue("f"), new UnsupportError("Unspport Error"));
        assertEquals(args.getValue("l"), TRUE);
        assertEquals(args.getValue("d"), new Integer(1));
        assertEquals(args.getValue("f"), "/usr/local");
        assertEquals(args.getValue("s"), null);
    }
}
