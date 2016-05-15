package ca.ece.ubc.cpen221.mp5.queryparser;

import java.util.ArrayList;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.AndexprContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.AtomContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.CategoryContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.InContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.NameContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.OrexprContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.PriceContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.RatingContext;
import ca.ece.ubc.cpen221.mp5.queryparser.QueryParser.RootContext;
import javafx.scene.text.Text;

public class QListener implements QueryListener {
    private static Stack<Query> stack = new Stack<Query>();
    public static int stackIndex = 0;

    @Override
    public void enterEveryRule(ParserRuleContext arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitEveryRule(ParserRuleContext arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitErrorNode(ErrorNode arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visitTerminal(TerminalNode arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterIn(InContext ctx) {
        TerminalNode in = ctx.STRING();
        String string = in.toString();
        In inquery = new In(string);
        stack.push(inquery);
    
    }

    @Override
    public void exitIn(InContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterPrice(PriceContext ctx) {
        TerminalNode price = ctx.RANGE();
        String string = price.toString();
        Price pricequery = new Price(string);
        stack.push(pricequery);
        
    }

    @Override
    public void exitPrice(PriceContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterRoot(RootContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitRoot(RootContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterOrexpr(OrexprContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitOrexpr(OrexprContext ctx) {
       int numord = ctx.andexpr().size();
       ArrayList<Query> orlist = new ArrayList<Query>();
       
       while(numord != 0){
           orlist.add(stack.pop());
           numord--;
       }
       
       Or or = new Or(orlist);
       stack.push(or);
        
    }

    @Override
    public void enterName(NameContext ctx) {
      TerminalNode name = ctx.STRING();
      String string = name.toString();
      Query namequery = new Name(string);
      stack.push(namequery);
        
    }

    @Override
    public void exitName(NameContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterRating(RatingContext ctx) {
        TerminalNode rat = ctx.RANGE();
        String string = rat.toString();
        Rating ratquery = new Rating(string);
        stack.push(ratquery);
        
    }

    @Override
    public void exitRating(RatingContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterAtom(AtomContext ctx) {
        
        
    }

    @Override
    public void exitAtom(AtomContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterCategory(CategoryContext ctx) {
        TerminalNode cat = ctx.STRING();
        String string = cat.toString();
        Category catquery = new Category(string);
        stack.push(catquery);
        
    }

    @Override
    public void exitCategory(CategoryContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enterAndexpr(AndexprContext ctx) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void exitAndexpr(AndexprContext ctx) {
       ArrayList<Query> andlist = new ArrayList<Query>();
       
       int numatoms = ctx.atom().size();
           while (numatoms != 0){
               andlist.add(stack.pop());
               
               numatoms--;
           }
     And and = new And(andlist);
     stack.push(and);
    }
    
    public Query getQuery(){
        return stack.get(stackIndex++);
        
    }
    
}
  