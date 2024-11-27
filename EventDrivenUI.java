import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventDrivenUI {
  public static void main(String[] args) {
    // Create a LinkedList to manage student records
    LinkedList studentRecords = new LinkedList();

    // Create a JFrame (Main Window)
    JFrame frame = new JFrame("Student Record Program");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);

    // Create a JLabel 
    JLabel label = new JLabel("Menu", SwingConstants.CENTER);
    label.setBounds(150, 20, 100, 30);
    frame.add(label);

    // Create Buttons with unique positions
    JButton addButton = new JButton("Add Record");
    addButton.setBounds(50, 70, 150, 30);
    frame.add(addButton);

    JButton deleteButton = new JButton("Delete Record");
    deleteButton.setBounds(210, 70, 150, 30);
    frame.add(deleteButton);

    JButton updateButton = new JButton("Update Record");
    updateButton.setBounds(50, 120, 150, 30);
    frame.add(updateButton);

    JButton displayButton = new JButton("Display Records");
    displayButton.setBounds(210, 120, 150, 30);
    frame.add(displayButton);

    JButton exitButton = new JButton("Exit");
    exitButton.setBounds(130, 170, 120, 30);
    frame.add(exitButton);

    // Add ActionListeners to buttons
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Prompt user for record details
        String name = JOptionPane.showInputDialog(frame, "Enter student name:");
        if(studentRecords.search(name))
        {
          JOptionPane.showMessageDialog(frame, "Name Exist!", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        else{
          int quiz1 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Quiz 1 score:"));
          if(quiz1 > 100 || quiz1 < 1)
            {
              JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
              return;
            }

          int quiz2 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Quiz 2 score:"));
          if(quiz2 > 100 || quiz2 < 1)
            {
              JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
              return;
            }
          
          int quiz3 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Quiz 3 score:"));
          if(quiz3 > 100 || quiz1 < 3)
            {
              JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
              return;
            }

          // Add to linked list
          studentRecords.add(name, quiz1, quiz2, quiz3);
          JOptionPane.showMessageDialog(frame, "Record added successfully.");
        }
      }
    });

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (studentRecords.isEmpty())
        {
          JOptionPane.showMessageDialog(frame, "No records found.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        // Prompt user for the name of the student to delete
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the student to delete:");
        if(studentRecords.search(name))
        {
          studentRecords.delete(name);
          JOptionPane.showMessageDialog(frame, "Record deleted.");
        }
        else{
          JOptionPane.showMessageDialog(frame, "Record not found.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
      }
    });

    updateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (studentRecords.isEmpty())
        {
          JOptionPane.showMessageDialog(frame, "No records found.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
        else
        {
            // Prompt user for the name of the student to update
          String name = JOptionPane.showInputDialog(frame, "Enter the name of the student to update:");
          Node student = studentRecords.find(name);

          if (student == null) {
              JOptionPane.showMessageDialog(frame, "Student not found.");
          } 
          else 
          {
            int quizNum = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter quiz number to update: "));
            int newQuiz1, newQuiz2, newQuiz3;
            switch (quizNum) 
            { 
              case 1:
                newQuiz1 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new Quiz 1 score:"));
                if(newQuiz1 > 100 || newQuiz1 < 1)
                {
                  JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                  student.quiz1 = newQuiz1;
                  JOptionPane.showMessageDialog(frame, "Record updated successfully.");
                }
                break;
              case 2:
                newQuiz2 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new Quiz 2 score:"));
                if(newQuiz2 > 100 || newQuiz2 < 1)
                {
                  JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                  student.quiz2 = newQuiz2;
                  JOptionPane.showMessageDialog(frame, "Record updated successfully.");
                }
                break;
              case 3:
                newQuiz3 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new Quiz 3 score:"));
                if(newQuiz3 > 100 || newQuiz3 < 1)
                {
                  JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                  student.quiz3 = newQuiz3;
                  JOptionPane.showMessageDialog(frame, "Record updated successfully.");
                }
                break;
              default:
                JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            }
          }
        }
      }
});

    displayButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (studentRecords.isEmpty())
          {
            JOptionPane.showMessageDialog(frame, "No records found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
          }
          else{
             // Display all student records
            String records = studentRecords.displayAll();
            JOptionPane.showMessageDialog(frame, records);
          }
        }
    });

    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
              "Exit Confirmation", JOptionPane.YES_NO_OPTION);
          if (response == JOptionPane.YES_OPTION) {
              System.exit(0);
          }
        }
    });

    // Show the JFrame
    frame.setVisible(true);
  }
}

// Node class to represent student records
class Node {
    String name;
    int quiz1;
    int quiz2;
    int quiz3;
    Node next;

    public Node(String name, int quiz1, int quiz2, int quiz3) {
        this.name = name;
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.next = null;
    }
}

// LinkedList class to manage the student records
class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public Boolean isEmpty(){
      if(head == null) return true;
      else return false;
    }

    public void add(String name, int quiz1, int quiz2, int quiz3) {
      Node newNode = new Node(name, quiz1, quiz2, quiz3);
      if (head == null) {
          head = newNode;
      } else {
          Node current = head;
          while (current.next != null) {
              current = current.next;
          }
          current.next = newNode;
      }
    }

    public void delete(String name) {
      if (head == null) {
          return;
      }
      if (head.name.equals(name)) {
          head = head.next;
          return;
      }
      Node current = head;
      Node prev = null;
      while (current != null) 
      {
        if(current.name.equals(name))
        {
          prev.next = current.next;
          return;
        }
        prev = current;
        current = current.next;
      }
    }

    public Boolean search(String name)
    {
      Node current = head;
      while (current != null) {
        if (current.name.equals(name)) {
            return true;
        }
        current = current.next;
      }
      return false;
    }
    public Node find(String name) {
      Node current = head;
      while (current != null) {
          if (current.name.equals(name)) {
              return current;
          }
          current = current.next;
      }
      return null;
    }

    public float computeAve(int q1, int q2, int q3)
    {
      float average = (q1+q2+q3)/3;
      return average;
    }

    public String displayAll() {
      if (head == null) {
          return "No records to display.\n";
      }
  
      StringBuilder sb = new StringBuilder();
  
      // Header row
      sb.append(String.format("%-15s %-7s %-7s %-7s %-10s %-10s\n", 
        "Name", "Quiz1", "Quiz2", "Quiz3", "Average", "Remarks"));
      sb.append("------------------------------------------------------------------\n");
  
      Node current = head;
  
      // Data rows
      while (current != null) {
          float average = computeAve(current.quiz1, current.quiz2, current.quiz3);
          String remarks = average >= 75 ? "PASSED" : "FAILED";
  
          sb.append(String.format("%-15s %-7d %-7d %-7d %-10.2f %-10s\n",
              current.name, current.quiz1, current.quiz2, current.quiz3, average, remarks));

          current = current.next;
      }
  
      return sb.toString();
  }
  
}
