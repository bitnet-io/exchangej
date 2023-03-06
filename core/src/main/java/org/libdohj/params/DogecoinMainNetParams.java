/*
 * Copyright 2013 Google Inc.
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

package org.libdohj.params;


import java.net.URI;


import org.bitcoinj.core.Block;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bitcoinj.net.discovery.HttpDiscovery;


import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main Dogecoin production network on which people trade
 * goods and services.
 */
public class DogecoinMainNetParams extends AbstractDogecoinParams {
    public static final int MAINNET_MAJORITY_WINDOW = 2000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 1900;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 1500;
    private static final long GENESIS_TIME = 1677414786;
    private static final long GENESIS_NONCE = 1196422;
    private static final Sha256Hash GENESIS_HASH = Sha256Hash.wrap("00000caff19214512f927af2b7cead4b33a54c096e432a275628cb4cf8d4b73c");

    public static final long STANDARD_MAX_DIFFICULTY_TARGET = 0x1d00ffffL;

    protected static final int DIFFICULTY_CHANGE_TARGET = 100000;

    public DogecoinMainNetParams() {
        super(DIFFICULTY_CHANGE_TARGET);

        id = ID_MAINNET;

        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(STANDARD_MAX_DIFFICULTY_TARGET);

        // not in this case would be 188 ... since 60 + 128 = 188 .......in this case its my own weird thing that still works as 30 + 128 for 158 from the standard desktop qt wallet from dogecoin actually using the proper value of address_prefix + 128(still works for radiocoin-qt as 158 as a customized value to decode the private key from address_prefix 60 "R")
        dumpedPrivateKeyHeader = 158; //This is always addressHeader + 128
        addressHeader = 25;
        p2shHeader = 22;
        port = 8333;
        packetMagic = 0xd1e1d1e1;
        segwitAddressHrp = "bit";
        // Note that while BIP44 makes HD wallets chain-agnostic, for legacy
        // reasons we use a Doge-specific header for main net. At some point
        // we'll add independent headers for BIP32 legacy and BIP44.
         bip32HeaderP2PKHpub = 0x0488b21e; // The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderP2PKHpriv = 0x0488ade4; // The 4 byte header that serializes in base58 to "xprv"
        bip32HeaderP2WPKHpub = 0x04b24746; // The 4 byte header that serializes in base58 to "zpub".
        bip32HeaderP2WPKHpriv = 0x04b2430c; // The 4 byte header that serializes in base58 to "zprv"
        
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setTime(1677414786L);
        genesisBlock.setNonce(1196422L);
        subsidyDecreaseBlockCount = 100000;
        spendableCoinbaseDepth = 10;

        // Note this is an SHA256 hash, not a Scrypt hash. Scrypt hashes are only
        // used in difficulty calculations.
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
        checkpoints.put(0, Sha256Hash.wrap("00000caff19214512f927af2b7cead4b33a54c096e432a275628cb4cf8d4b73c"));

        
        
  // ?? risky business readding dogecoin dns seeds here to attempt experimentation with peer to peer "bloom filter bit" that might be missing from radiocoin dns seed points for android wallet support (in experimentation to match protocol header)
        dnsSeeds = new String[] {
                "seed.bitexplorer.io",
            "bitexplorer.io",
            "us.mining4people.com",
            "fi.mining4people.com",
            };
    }
    private static DogecoinMainNetParams instance;
    public static synchronized DogecoinMainNetParams get() {
        if (instance == null) {
            instance = new DogecoinMainNetParams();
        }
        return instance;
    }
   
    /*
    @Override
    public Block getGenesisBlock() {
        synchronized (GENESIS_HASH) {
            if (genesisBlock == null) {
                genesisBlock = Block.createGenesis(this);
                genesisBlock.setDifficultyTarget(STANDARD_MAX_DIFFICULTY_TARGET);
                genesisBlock.setTime(GENESIS_TIME);
                genesisBlock.setNonce(GENESIS_NONCE);
                checkState(genesisBlock.getHash().equals(GENESIS_HASH), "Invalid genesis hash");
            }
        }
        return genesisBlock;
    }
*/
    
    @Override
    public boolean allowMinDifficultyBlocks() {
        return false;
    }

    @Override
    public String getPaymentProtocolId() {
        // TODO: CHANGE THIS
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }

    @Override
    public boolean isTestNet() {
        return false;
    }
}
