## CS 4386.001, Compiler Design, Fall 2022
# Project Assignment #2 Phase 1
### James Schuebel

How to run:

    - put all test cases within testfiles
    
    - enter "make parserTests" or "make lexerTests" on the command line
    
    - if it was successful, output will be in lexerTestsOutputs and parserTestsOutputs

------------------------------------------------------

Program → Stmts

Stmts → Stmt Stmts | λ

Stmt → if ( Expr ) { Stmts } IfEnd | while ( Expr ) { Stmts } | Name = Expr ;
| read ( Readlist ) ; | print ( Printlist ) ; | printline ( Printlinelist ) ;
| id ( ) ; | id ( Args ) ; | return ; | return Expr ; | Name ++ ; | Name -- ;
| { Stmts } Optionalsemi

IfEnd → else { Stmts } | λ

Name → id | id [ Expr ]

Args → Expr , Args | Expr

Readlist → Name , Readlist | Name

Printlist → Expr , Printlist | Expr

Printlinelist → Printlist | λ

Expr → Name | id ( ) | id ( Args ) | intlit | charlit | strlit | floatlit | true | false
| ( Expr ) | ~ Expr | - Expr | + Expr | ( Type ) Expr |
| Expr Binaryop Expr | ( Expr ? Expr : Expr )

Binaryop → * | / | + | - | < | > | <= | >= | == | <> | \|\| | &&

(), [] Left to Right

(prefix)+, (prefix)-,~,++,-- Right to Left

(type cast) Left to Right

*,/ Left to Right

+,- Left to Right

<,>,<=,>= Left to Right

<>,== Left to Right

&& Left to Right

|| Left to Right

?: Right to Lef
