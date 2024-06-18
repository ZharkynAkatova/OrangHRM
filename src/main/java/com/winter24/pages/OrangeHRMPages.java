package com.winter24.pages;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder

public class OrangeHRMPages {

   protected HomePage homePage;
   protected AdminPage adminPage;

    public void setUp() {
       homePage = new HomePage();
       adminPage = new AdminPage();
    }
}
