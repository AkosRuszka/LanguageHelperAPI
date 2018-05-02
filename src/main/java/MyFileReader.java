import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MyFileReader {

	public static void start() {
		
		try {
			Path path = Paths.get("/media/akos/Adat/kezdo_szavak");
			
			Stream<String> lines = Files.lines(path);
			
			lines.forEach(line -> LangHelper.addNewRegistry(line));
			
			lines.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
