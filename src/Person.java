public class Person {
    String role,name,email,phone,addresse,dob,pass;
    Person(String role,String name,String email,String phone,String addresse,String dob,String pass)
    {
        this.addresse=addresse;
        this.role=role;
        this.dob=dob;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.pass=pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
