package com.util.ai.screenbot.output.elements.types;

import com.util.ai.screenbot.output.elements.VBBalanceElement;
import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.elements.VBBrowsingStatusElement;
import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.elements.gui.VBGuiElement;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBalanceGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMaxStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerMinStakeGui;
import com.util.ai.screenbot.output.elements.gui.bookie.VBBookmakerOddsGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBetInfoGui;
import com.util.ai.screenbot.output.elements.gui.general.VBBrowsingStatusGui;
import com.util.ai.screenbot.output.elements.gui.general.VBSingleBetGui;
import com.util.ai.screenbot.output.interpreters.VBElementInterpreter;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.betInfoInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.bookmakerOddsInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.singleBetInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.browsingStatusInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.balanceInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.bookmakerMinStakeInterpreter;
import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.bookmakerMaxStakeInterpreter;


public enum VBScreenElementType {
	
	BOOKMAKER_MIN_STAKE() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBookmakerMinStakeElement, ? super VBBookmakerMinStakeGui> getInterpreter() {
			return bookmakerMinStakeInterpreter();
		}
	},
	
	BOOKMAKER_MAX_STAKE() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBookmakerMaxStakeElement, ? super VBBookmakerMaxStakeGui> getInterpreter() {
			return bookmakerMaxStakeInterpreter();
		}
	},
	
	BROWSING_STATUS() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBrowsingStatusElement, VBBrowsingStatusGui> getInterpreter() {
			return browsingStatusInterpreter();
		}
	},
	
	BALANCE() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBalanceElement, ? super VBBalanceGui> getInterpreter() {
			return balanceInterpreter();
		}
	},

	SINGLE_BET() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBSingleBetElement, VBSingleBetGui> getInterpreter() {
			return singleBetInterpreter();
		}
	},
	
	BOOKMAKER_ODDS() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBookmakerOddsElement, ? super VBBookmakerOddsGui> getInterpreter() {
			return bookmakerOddsInterpreter();
		}
	},
	
	BET_INFO() {
		
		@Override
		@SuppressWarnings("unchecked")
		public VBElementInterpreter<VBBetInfoElement, VBBetInfoGui> getInterpreter() {
			return betInfoInterpreter();
		}
	};
	
	public <E extends VBScreenElement, G extends VBGuiElement> VBElementInterpreter<E, G> getInterpreter() {
		// should be overriden in enum values
		return null;
	}
}
