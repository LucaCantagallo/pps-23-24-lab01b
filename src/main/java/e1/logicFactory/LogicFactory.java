package e1.logicFactory;

import e1.logic.Logics;

public interface LogicFactory {

    Logics createLogic(int size);

    Logics createCleanLogic(int size);

}
