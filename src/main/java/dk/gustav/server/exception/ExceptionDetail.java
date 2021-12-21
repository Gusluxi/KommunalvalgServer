package dk.gustav.server.exception;

import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class ExceptionDetail {
    private final int status;
    private final String message;
    private final String path;
    private final ZonedDateTime timestamp;
    public ExceptionDetail(int status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Europe/Copenhagen"));
    }
}