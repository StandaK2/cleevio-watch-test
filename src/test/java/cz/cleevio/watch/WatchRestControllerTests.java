package cz.cleevio.watch;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.FileCopyUtils;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class WatchRestControllerTests {

    private static final String TEST_FILES_DIR = "testfiles/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void whenJsonPostRequestToUploadWatchAndValidWatch_thenCorrectResponse() throws Exception {
        String jsonReqBody = convertFileToString("watch.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/watch/upload")
                .content(jsonReqBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void whenXmlPostRequestToUploadWatchAndValidWatch_thenCorrectResponse() throws Exception {
        String xmlReqBody = convertFileToString("watch.xml");

        mockMvc.perform(MockMvcRequestBuilders.post("/watch/upload")
                .content(xmlReqBody)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void whenJsonPostRequestToUploadWatchAndNotValidWatch_thenJsonApiErrorResponse() throws Exception {
        String jsonReqBody = convertFileToString("watch_invalid.json");

        String apiValidationErrors = "$.apiValidationErrors[?(@.object == '%s')]";

        mockMvc.perform(MockMvcRequestBuilders.post("/watch/upload")
                .content(jsonReqBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Field validation error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath(apiValidationErrors, "title").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(apiValidationErrors, "price").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(apiValidationErrors, "fountain").exists());
    }

    @Test
    public void whenXmlPostRequestToUploadWatchAndNotValidWatch_thenXmlApiErrorResponse() throws Exception {
        String xmlReqBody = convertFileToString("watch_invalid.xml");
        String xmlApiError = convertFileToString("apiError.xml");

        String apiValidationErrors = "//apiValidationErrors[%s]";
        mockMvc.perform(MockMvcRequestBuilders.post("/watch/upload")
                .content(xmlReqBody)
                .accept(MediaType.APPLICATION_XML)
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.xpath("//message").string("Field validation error"))
                .andExpect(MockMvcResultMatchers.xpath("//status").string("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.xpath(apiValidationErrors, "object[text()='title']").exists())
                .andExpect(MockMvcResultMatchers.xpath(apiValidationErrors, "object[text()='price']").exists())
                .andExpect(MockMvcResultMatchers.xpath(apiValidationErrors, "object[text()='fountain']").exists());
    }

    private String convertFileToString(String filename) {
        ClassLoader classLoader = resourceLoader.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(TEST_FILES_DIR + filename);

        try (Reader reader = new InputStreamReader(inputStream, UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
