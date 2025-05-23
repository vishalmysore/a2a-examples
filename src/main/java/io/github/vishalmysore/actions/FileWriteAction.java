package io.github.vishalmysore.actions;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Log
@Agent(groupName = "FileWriteAgent", groupDescription = "FileWriteAgent")
public class FileWriteAction  {

    @Action(description = "saves the information in local file")
    public Object saveInformationToLocalFile(String args[]) {
        StringBuilder content = new StringBuilder();
        for (String arg : args) {
            content.append(arg).append("\n");
        }

        String fileName = null;
        FileWriter writer = null;
        try {
            // Create a temporary file
            File tempFile = File.createTempFile("temp", ".txt");
            fileName = tempFile.getAbsolutePath();

            // Write content to the temporary file
            writer = new FileWriter(tempFile);
            writer.write(content.toString());
            log.info("Information saved to local file: " + fileName);
        } catch (IOException e) {
            log.severe("Failed to save information to local file: " + e.getMessage());
        } finally {
            // Close the writer
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.severe("Failed to close writer: " + e.getMessage());
                }
            }
        }

        return fileName;
    }


}