package entities;

public class Customer {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postcode;
    private String phoneNumber;
    private String userEmail;
    private String userPassword;
    private String country;

    public String getCountry() {
        return country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public static Builder newEntity() {
        return new Customer().new Builder();
    }

    public class Builder{
        private Builder(){}
        public Builder withFirstName(String firstName) {
            Customer.this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            Customer.this.lastName = lastName;
            return this;
        }

        public Builder withAddress(String address) {
            Customer.this.address = address;
            return this;
        }

        public Builder withCity(String city) {
            Customer.this.city = city;
            return this;
        }

        public Builder withPostcode(String postcode) {
            Customer.this.postcode = postcode;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            Customer.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withUserEmail(String userEmail) {
            Customer.this.userEmail = userEmail;
            return this;
        }

        public Builder withUserPassword(String userPassword) {
            Customer.this.userPassword = userPassword;
            return this;
        }

        public Builder withCountry(String country) {
            Customer.this.country = country;
            return this;
        }

        public Customer build() {
            return Customer.this;
        }
    }
}
