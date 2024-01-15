package org.example.SQLite;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Piloto {
    private int driverid;
    private String code;
    private String forename;
    private String surname;
    private String dob;
    private String nationality;
    private String url;

}
