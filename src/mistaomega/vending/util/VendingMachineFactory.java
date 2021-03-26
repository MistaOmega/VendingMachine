package mistaomega.vending.util;

import mistaomega.vending.machine.IVendingMachine;
import mistaomega.vending.machine.VendingMachine;

/**
 * Unsure if needed for proj, would theoretically allow the construction of different vending machine "types"
 * //Unimplemented
 *
 */
public class VendingMachineFactory {
    public static IVendingMachine createVendingMachine() {
        return new VendingMachine();
    }
}
