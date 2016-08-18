package client.android.com.retrifittutorial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Akshay.Borgave on 01-06-2016.
 */
public class ContactJson {

   public ArrayList<Contact> getContacts() {
      return contacts;
   }

   public void setContacts(ArrayList<Contact> contacts) {
      this.contacts = contacts;
   }

   @SerializedName("contacts")
   ArrayList<Contact> contacts;

}
