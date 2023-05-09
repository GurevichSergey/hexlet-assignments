package exercise;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles (
            String pathFirstFile,
            String pathSecondFile,
            String  writePathFile) {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {

            Path path = Paths.get(pathFirstFile).toAbsolutePath().normalize();
            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {

            Path path = Paths.get(pathSecondFile).toAbsolutePath().normalize();
            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> futureResult = future1.thenCombine(future2, (file1, file2) -> {
            Path path = Paths.get(writePathFile).toAbsolutePath().normalize();
            var file = file1 + file2;

            try {
                Files.writeString(path, file,StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            return file;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });

        return futureResult;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/file3.txt");
        result.get();
        // END
    }
}

