package webtests.seleniumeasy;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.configuration.SessionLocalTempDirectory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webtests.seleniumeasy.pageobjects.DownloadPage;

import java.io.File;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@RunWith(SerenityRunner.class)
@WithTag("local")
public class WhenDownloadingFiles {

    @Managed
    WebDriver driver;

    DownloadPage downloadPage;

    @Test
    public void weCanDownloadAFileToOurHardDrive() {
        //open page
        downloadPage.open();

        //download a file
        String fileName = "some-file.txt";
        downloadPage.downloadSampleFileWithName(fileName);

        //check file exists
//        File downloadedFile = Paths.get("C:\\Users\\PC\\AppData\\Local\\Temp\\some-file.txt").toFile();
        File downloadedFile = SessionLocalTempDirectory.forTheCurrentSession().resolve(fileName).toFile();

        //wait until file downloads
//        Thread.sleep(2000); //-->
        await().atMost(ofSeconds(15)).until(
//                () -> downloadedFile.exists()
                downloadedFile::exists
        );

        assertThat(downloadedFile).exists();
        assertThat(downloadedFile.getName()).isEqualTo(fileName);
    }
}
