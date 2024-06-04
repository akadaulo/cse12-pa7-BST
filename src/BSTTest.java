import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {

	private FileSystem fileSystem;

	@Before
    public void setUp() {
        fileSystem = new FileSystem();
        fileSystem.add("file1.txt", "/documents", "2022/06/01");
        fileSystem.add("file2.txt", "/documents", "2022/06/02");
        fileSystem.add("file3.txt", "/pictures", "2022/06/01");
        fileSystem.add("file4.txt", "/pictures", "2022/06/02");
    }
	
	/* TODO: Add your own tests */
	@Test
	public void testAdd() {
        fileSystem.add("file5.txt", "/documents", "2022/06/03");
        assertEquals(5, fileSystem.outputNameTree().size());
        assertEquals(5, fileSystem.outputDateTree().size());
    }

    @Test
    public void testFindFileNamesByDate() {
        List<String> fileNames = fileSystem.findFileNamesByDate("2022/06/01");
        assertEquals(2, fileNames.size());
        assertTrue(fileNames.contains("file1.txt"));
        assertTrue(fileNames.contains("file3.txt"));
    }

    @Test
    public void testFilterByDate() {
        FileSystem filteredFileSystem = fileSystem.filter("2022/06/01", "2022/06/02");
        assertEquals(4, filteredFileSystem.outputNameTree().size());
        assertEquals(4, filteredFileSystem.outputDateTree().size());
    }

    @Test
    public void testFilterByWildCard() {
        FileSystem filteredFileSystem = fileSystem.filter("file2");
        assertEquals(1, filteredFileSystem.outputNameTree().size());
        assertEquals(1, filteredFileSystem.outputDateTree().size());
        assertTrue(filteredFileSystem.outputNameTree().get(0).contains("file2.txt"));
    }

    @Test
    public void testOutputNameTree() {
        List<String> outputList = fileSystem.outputNameTree();
        assertEquals(4, outputList.size());
        assertTrue(outputList.contains("file1.txt: {Name: file1.txt, Directory: /documents, Modified Date: 2022/06/01}"));
    }

    @Test
    public void testOutputDateTree() {
        List<String> outputList = fileSystem.outputDateTree();
        assertEquals(4, outputList.size());
        assertTrue(outputList.contains("2022/06/01: {Name: file1.txt, Directory: /documents, Modified Date: 2022/06/01}"));
    }




	
}
