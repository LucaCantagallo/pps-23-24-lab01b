package e1.logicFactory;

import e1.logic.CleanLogicsImpl;
import e1.logic.Logics;
import e1.logic.LogicsImpl;

public class LogicFactoryImpl implements LogicFactory {

    public LogicFactoryImpl(){

    }

    @Override
    public Logics createLogic(int size) {
        return new LogicsImpl(size);
    }

    @Override
    public Logics createCleanLogic(int size) {
        return new CleanLogicsImpl(size);
    }

}
