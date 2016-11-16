package com.example.beata_macbook.opentricity.UI.Model;

/**
 * Created by user on 15.11.2016.
 */

public class NewPlace {


        private final String name;

        private final String address;

        public NewPlace(final String name, final String address) {
            this.name = name;
            this.address = address;
        }

        public String getTextName() {
            return name;
        }

        public String getTextAdr() { return address;
        }


}


