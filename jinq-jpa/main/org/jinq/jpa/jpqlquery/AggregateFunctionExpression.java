package org.jinq.jpa.jpqlquery;


public class AggregateFunctionExpression extends Expression
{
   final Expression base;
   final String aggregateName;
   public AggregateFunctionExpression(Expression base, String aggregateName)
   {
      this.base = base;
      this.aggregateName = aggregateName;
   }
   
   @Override
   public void generateQuery(QueryGenerationState queryState, OperatorPrecedenceLevel operatorPrecedenceScope)
   {
      queryState.appendQuery(aggregateName);
      queryState.appendQuery("(");
      base.generateQuery(queryState, OperatorPrecedenceLevel.JPQL_UNRESTRICTED_OPERATOR_PRECEDENCE);
      queryState.appendQuery(")");
   }

   @Override
   public void prepareQueryGeneration(
         QueryGenerationPreparationPhase preparePhase,
         QueryGenerationState queryState)
   {
      base.prepareQueryGeneration(preparePhase, queryState);
   }
   
   @Override public boolean equals(Object obj)
   {
      if (!getClass().equals(obj.getClass())) return false;
      AggregateFunctionExpression o = (AggregateFunctionExpression)obj; 
      return base.equals(o.base) && aggregateName.equals(o.aggregateName);
   }

   @Override
   public void visit(ExpressionVisitor visitor)
   {
      visitor.visitAggregateFunction(this);
   }
}
