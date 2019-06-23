import com.tencent.jace.practice.Args;
import com.tencent.jace.practice.UnsupportError;
import org.junit.Ignore;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertEquals(args.getValue("d"), new Integer(8010));
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
        //assertEquals(args.getValue("f"), new UnsupportError("Unspport Error"));
        assertEquals(args.getValue("l"), TRUE);
        assertEquals(args.getValue("d"), new Integer(1));
        assertEquals(args.getValue("f"), "/usr/local");
        assertEquals(args.getValue("s"), null);
    }

    @Test
    public void testParseSchema() {
        Args args = new Args("l:bool", "");
        assertNotNull(args.getShemaMaps());
        assertEquals(args.getShemaMaps().get("l"), Boolean.class);
        args = new Args("l:bool,d:int,f:string,s:string", "-l TRue -d 001 -f /usr/ local -s");
        assertEquals(args.getShemaMaps().get("d"), Integer.class);
        assertEquals(args.getShemaMaps().get("f"), String.class);
        assertEquals(args.getValue("x"), new UnsupportError("Unspport Error"));
    }

    @Test
    public void testParseCommandLine() {
        Args args = new Args("l:bool,d:int,f:string,s:string", "");
        args.parseCommandLine("-l TRue");
        assertNotNull(args.getCommandMaps());
        assertEquals(args.getCommandMaps().get("l"), "true");

        args.parseCommandLine("-d 001");
        assertEquals(args.getCommandMaps().get("d"), "001");

        args.parseCommandLine("-f /usr/local");
        assertEquals(args.getCommandMaps().get("f"), "/usr/local");

        args.parseCommandLine("-x");
        assertEquals(args.getCommandMaps().get("x"), null);
    }

    @Test
    public void testGetCommandMaps() {
        Args args = new Args("l:bool", "-l TRue");
        assertNotNull(args.getCommandMaps());
        assertEquals(args.getCommandMaps().get("l"), "true");
    }

    @Test
    public void testGetVal() {
        Args args = new Args("l:bool,d:int,f:string", "-l TRue -d 8080 -f /usr/local");

        assertNotNull(args.getValue("l"));
        assertEquals(args.getValue("l"), TRUE);
        assertEquals(args.getValue("x"), new UnsupportError("Unspport Error"));

        assertEquals(args.getValue("d"), new Integer(8080));
    }

    @Test
    public void testGetKeyClass() {
        Args args = new Args("l:bool,d:int,f:string", "-l TRue -d 8080 -f /usr/local");
        assertEquals(args.getKeyClass("bool"), Boolean.class);
        assertEquals(args.getKeyClass("int"), Integer.class);
        assertEquals(args.getKeyClass("sTrIng"), String.class);
    }

    @Test
    public void testGetDefaultArg() {
        Args args = new Args("l:bool,d:int,f:string", "-l TRue -d 8080 -f /usr/local");
        assertEquals(args.getDefaultArg("l"), FALSE);
        assertEquals(args.getDefaultArg("d"), 0);
        assertEquals(args.getDefaultArg("f"), ".");
        assertEquals(args.getDefaultArg("s"), null);
    }

    @Test
    public void testIsAvaliableSchemaKey() {
        Args args = new Args("l:bool,d:int,f:string,s:string", "-l TRue -d 8080 -f /usr/local");
        assertEquals(args.isAvaliableSchemaKey("-l"), TRUE);
        assertEquals(args.isAvaliableSchemaKey("-d"), TRUE);
        assertEquals(args.isAvaliableSchemaKey("-f"), TRUE);
        assertEquals(args.isAvaliableSchemaKey("-s"), TRUE);
        assertEquals(args.isAvaliableSchemaKey("-x"), FALSE);
    }

}
