package framework;

import java.util.List;
import java.util.LinkedList;

public class IterativeDeepningEngine<S extends State, Problem extends AbstractSearchProblem<S>> extends AbstractSearchEngine<S,Problem> {
  private List<S> visited;
  private List<S> path;

  public IterativeDeepningEngine(){
    super();
    visited = new LinkedList<S>();
    path = new LinkedList<S>();
  }

  public IterativeDeepningEngine(Problem p){
    super(p);
    visited = new LinkedList<S>();
    path = new LinkedList<S>();
  }


  private boolean recursiveIterativeDeepening(S s, int depth){
      if(!visited.contains(s) && depth>0){
        visited.add(0,s);
        if(problem.success(s)){
          path.add(0,s);
          return true;
        }
        else{
          List<S> succesors = problem.getSuccessors(s);
          boolean found = false;
          S child;
          while(!succesors.isEmpty() && !found){
            child = succesors.get(0);
            succesors.remove(0);
            found = recursiveIterativeDeepening(child,depth-1);
            if(found){
              path.add(0,s);
              return found;
            }
          }
          return found;
        }
      }
      else{
        return false;
      }
  }

  public boolean performSearch() {

    // first, we initialise the data structures for the search
    visited = new LinkedList<S>();
    // we get the initial state
    S initialState = problem.initialState();

    // now we call a recursive method implementing depth-first
    boolean resultSearch = false;
    int i = 0;
    while(!resultSearch){
      resultSearch = recursiveIterativeDeepening(initialState,i);
      visited = new LinkedList<S>();
      i++;
    }
    return resultSearch;

  }
  public List<S> getPath() {
    return path;
  }

  public void report() {
    for(int i = path.size()-1; i >=0;i--){
      System.out.println(path.get(i));
    }
  }

}
