//package entities;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//@T
//public class Expression {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//    private Long id;
//    private String expression;
//    private String meaning;
//
//    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getExpression() {
//        return expression;
//    }
//
//    public void setExpression(String expression) {
//        this.expression = expression;
//    }
//
//    public String getMeaning() {
//        return meaning;
//    }
//
//    public void setMeaning(String meaning) {
//        this.meaning = meaning;
//    }
//
//    @Override
//    public String toString() {
//        return expression + " - " + meaning;
//    }
//}
