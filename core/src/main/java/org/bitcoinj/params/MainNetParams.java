/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;
import java.math.BigInteger;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
//         maxTarget = Utils.decodeCompactBits(0x1e0ffff0L);
//        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        maxTarget = Utils.decodeCompactBits(0x207fffffL);
        dumpedPrivateKeyHeader = 158;
        addressHeader = 25;
        p2shHeader = 22;
        segwitAddressHrp = "bit";
        port = 8333;
        packetMagic = 0xd1e1d1e1L;
        //bip32HeaderP2PKHpub = 0x02facafd; //The 4 byte header that serializes in base58 to "dgub".
        //bip32HeaderP2PKHpriv =  0x02fac398; 

        bip32HeaderP2PKHpub = 0x0488b21e; // The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderP2PKHpriv = 0x0488ade4; // The 4 byte header that serializes in base58 to "xprv"
        bip32HeaderP2WPKHpub = 0x04b24746; // The 4 byte header that serializes in base58 to "zpub".
        bip32HeaderP2WPKHpriv = 0x04b2430c; // The 4 byte header that serializes in base58 to "zprv"


        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1677414786L);
        genesisBlock.setNonce(1196422L);
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 10;

        id = ID_MAINNET;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("00000caff19214512f927af2b7cead4b33a54c096e432a275628cb4cf8d4b73c"),
                genesisHash);

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
       // checkpoints.put(9300, Sha256Hash.wrap("000000000000002ea27d231207c64374bac81971cfe1f3f32fb50309f1b3a215"));

        
        
  // ?? risky business readding dogecoin dns seeds here to attempt experimentation with peer to peer "bloom filter bit" that might be missing from radiocoin dns seed points for android wallet support (in experimentation to match protocol header)
        dnsSeeds = new String[] {
                "bitexplorer.io",
            "seed.bitexplorer.io",
            "us.mining4people.com",
            "fi.mining4people.com",
            };
    }
    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
	
