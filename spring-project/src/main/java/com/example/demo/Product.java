import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "price")
  private double price;
  
  @Column(name = "stock")
  private int stock;
  
  @Column(name = "image")
  private String image;
  
  // getters and setters
  
}