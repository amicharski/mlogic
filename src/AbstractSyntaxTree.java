public class AbstractSyntaxTree {
    abstract class Stmt {
        private Stmt stmt;

        protected Stmt(Stmt stmt) {
            this.stmt = stmt;
        }

        Stmt getStmt(){return stmt;}
        void setStmt(Stmt stmt){this.stmt = stmt;}
    }
    abstract class Func extends Stmt {

        protected Func() {
            super(stmt);
        }
    }
    abstract class Expr extends Stmt {

        Expr() {
            super(stmt);
        }
    }
    class Iden {

    }
    class Prod {
        private Prod prod;

        protected Prod(Prod prod){
            this.prod = prod;
        }

        public Prod getProd() {
            return prod;
        }

        public void setProd(Prod prod) {
            this.prod = prod;
        }
    }
}
