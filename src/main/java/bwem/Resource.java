// Original work Copyright (c) 2015, 2017, Igor Dimitrijevic
// Modified work Copyright (c) 2017-2018 OpenBW Team

//////////////////////////////////////////////////////////////////////////
//
// This file is part of the BWEM Library.
// BWEM is free software, licensed under the MIT/X11 License.
// A copy of the license is provided with the library in the LICENSE file.
// Copyright (c) 2015, 2017, Igor Dimitrijevic
//
//////////////////////////////////////////////////////////////////////////

package bwem;

import bwapi.Unit;

/**
 * A Resource is either a Mineral or a Geyser.
 */
public abstract class Resource extends Neutral {
    Resource(final Unit unit, final BWMap map) {
        super(unit, map);
    }

    /**
     * Returns the initial amount of resources for this Resource (same as
     * unit()->getInitialResources).
     */
    public int getInitialAmount() {
        return getUnit().getInitialResources();
    }

    /**
     * Returns the current amount of resources for this Resource (same as unit()->getResources).
     */
    public int getAmount() {
        return getUnit().getResources();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Resource)) {
            return false;
        } else {
            final Resource that = (Resource) object;
            return (this.getUnit().getID() == that.getUnit().getID());
        }
    }

    @Override
    public int hashCode() {
        return getUnit().hashCode();
    }
}
