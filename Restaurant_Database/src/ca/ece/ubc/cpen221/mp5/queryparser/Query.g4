grammar Query;



root : orexpr ;
orexpr : andexpr(OR andexpr)*;
andexpr : atom(AND atom)*;
atom : in|category|rating|price|name|LPAREN orexpr RPAREN;


in : 'in' LPAREN STRING RPAREN ; 
category : 'category' LPAREN STRING RPAREN; 
name : 'name' LPAREN STRING RPAREN ;
rating : 'rating' LPAREN RANGE RPAREN ;
price : 'price' LPAREN RANGE RPAREN ;

OR : '||';
AND : '&&';
STRING : '"' ([a-zA-Z0-9]|' ')+ '"';
RANGE : [1-5]'..'[1-5];
LPAREN : '(';
RPAREN : ')';
WHITESPACE : [ \t\r\n]+ -> skip ;