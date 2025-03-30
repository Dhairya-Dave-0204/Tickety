package com.example.tickety.service;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 5.0.0.
 */
@SuppressWarnings("rawtypes")
public class NFTContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50336040518060400160405280600b81526020017f4576656e745469636b65740000000000000000000000000000000000000000008152506040518060400160405280600381526020017f544b540000000000000000000000000000000000000000000000000000000000815250816000908161008d919061043d565b50806001908161009d919061043d565b505050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036101125760006040517f1e4fbdf70000000000000000000000000000000000000000000000000000000081526004016101099190610550565b60405180910390fd5b6101218161012760201b60201c565b5061056b565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061026e57607f821691505b60208210810361028157610280610227565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026102e97fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826102ac565b6102f386836102ac565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b600061033a6103356103308461030b565b610315565b61030b565b9050919050565b6000819050919050565b6103548361031f565b61036861036082610341565b8484546102b9565b825550505050565b600090565b61037d610370565b61038881848461034b565b505050565b5b818110156103ac576103a1600082610375565b60018101905061038e565b5050565b601f8211156103f1576103c281610287565b6103cb8461029c565b810160208510156103da578190505b6103ee6103e68561029c565b83018261038d565b50505b505050565b600082821c905092915050565b6000610414600019846008026103f6565b1980831691505092915050565b600061042d8383610403565b9150826002028217905092915050565b610446826101ed565b67ffffffffffffffff81111561045f5761045e6101f8565b5b6104698254610256565b6104748282856103b0565b600060209050601f8311600181146104a75760008415610495578287015190505b61049f8582610421565b865550610507565b601f1984166104b586610287565b60005b828110156104dd578489015182556001820191506020850194506020810190506104b8565b868310156104fa57848901516104f6601f891682610403565b8355505b6001600288020188555050505b505050505050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061053a8261050f565b9050919050565b61054a8161052f565b82525050565b60006020820190506105656000830184610541565b92915050565b6126838061057a6000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c8063715018a6116100a2578063b88d4fde11610071578063b88d4fde146102a4578063c87b56dd146102c0578063e985e9c5146102f0578063eacabe1414610320578063f2fde38b146103505761010b565b8063715018a6146102425780638da5cb5b1461024c57806395d89b411461026a578063a22cb465146102885761010b565b806323b872dd116100de57806323b872dd146101aa57806342842e0e146101c65780636352211e146101e257806370a08231146102125761010b565b806301ffc9a71461011057806306fdde0314610140578063081812fc1461015e578063095ea7b31461018e575b600080fd5b61012a60048036038101906101259190611adb565b61036c565b6040516101379190611b23565b60405180910390f35b6101486103cd565b6040516101559190611bce565b60405180910390f35b61017860048036038101906101739190611c26565b61045f565b6040516101859190611c94565b60405180910390f35b6101a860048036038101906101a39190611cdb565b61047b565b005b6101c460048036038101906101bf9190611d1b565b610491565b005b6101e060048036038101906101db9190611d1b565b610593565b005b6101fc60048036038101906101f79190611c26565b6105b3565b6040516102099190611c94565b60405180910390f35b61022c60048036038101906102279190611d6e565b6105c5565b6040516102399190611daa565b60405180910390f35b61024a61067f565b005b610254610693565b6040516102619190611c94565b60405180910390f35b6102726106bd565b60405161027f9190611bce565b60405180910390f35b6102a2600480360381019061029d9190611df1565b61074f565b005b6102be60048036038101906102b99190611f66565b610765565b005b6102da60048036038101906102d59190611c26565b61078a565b6040516102e79190611bce565b60405180910390f35b61030a60048036038101906103059190611fe9565b61089d565b6040516103179190611b23565b60405180910390f35b61033a600480360381019061033591906120ca565b610931565b6040516103479190611daa565b60405180910390f35b61036a60048036038101906103659190611d6e565b610978565b005b6000634906490660e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614806103c657506103c5826109fe565b5b9050919050565b6060600080546103dc90612155565b80601f016020809104026020016040519081016040528092919081815260200182805461040890612155565b80156104555780601f1061042a57610100808354040283529160200191610455565b820191906000526020600020905b81548152906001019060200180831161043857829003601f168201915b5050505050905090565b600061046a82610ae0565b5061047482610b68565b9050919050565b61048d8282610488610ba5565b610bad565b5050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036105035760006040517f64a0ae920000000000000000000000000000000000000000000000000000000081526004016104fa9190611c94565b60405180910390fd5b60006105178383610512610ba5565b610bbf565b90508373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161461058d578382826040517f64283d7b00000000000000000000000000000000000000000000000000000000815260040161058493929190612186565b60405180910390fd5b50505050565b6105ae83838360405180602001604052806000815250610765565b505050565b60006105be82610ae0565b9050919050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036106385760006040517f89c62b6400000000000000000000000000000000000000000000000000000000815260040161062f9190611c94565b60405180910390fd5b600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b610687610dd9565b6106916000610e60565b565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6060600180546106cc90612155565b80601f01602080910402602001604051908101604052809291908181526020018280546106f890612155565b80156107455780601f1061071a57610100808354040283529160200191610745565b820191906000526020600020905b81548152906001019060200180831161072857829003601f168201915b5050505050905090565b61076161075a610ba5565b8383610f26565b5050565b610770848484610491565b61078461077b610ba5565b85858585611095565b50505050565b606061079582610ae0565b5060006006600084815260200190815260200160002080546107b690612155565b80601f01602080910402602001604051908101604052809291908181526020018280546107e290612155565b801561082f5780601f106108045761010080835404028352916020019161082f565b820191906000526020600020905b81548152906001019060200180831161081257829003601f168201915b505050505090506000610840611246565b90506000815103610855578192505050610898565b60008251111561088a5780826040516020016108729291906121f9565b60405160208183030381529060405292505050610898565b6108938461125d565b925050505b919050565b6000600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b600061093b610dd9565b6008600081548092919061094e9061224c565b91905055506000600854905061096484826112c6565b61096e81846112e4565b8091505092915050565b610980610dd9565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036109f25760006040517f1e4fbdf70000000000000000000000000000000000000000000000000000000081526004016109e99190611c94565b60405180910390fd5b6109fb81610e60565b50565b60007f80ac58cd000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161480610ac957507f5b5e139f000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b80610ad95750610ad882611340565b5b9050919050565b600080610aec836113aa565b9050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610b5f57826040517f7e273289000000000000000000000000000000000000000000000000000000008152600401610b569190611daa565b60405180910390fd5b80915050919050565b60006004600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600033905090565b610bba83838360016113e7565b505050565b600080610bcb846113aa565b9050600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614610c0d57610c0c8184866115ac565b5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610c9e57610c4f6000856000806113e7565b6001600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825403925050819055505b600073ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1614610d21576001600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b846002600086815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550838573ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a4809150509392505050565b610de1610ba5565b73ffffffffffffffffffffffffffffffffffffffff16610dff610693565b73ffffffffffffffffffffffffffffffffffffffff1614610e5e57610e22610ba5565b6040517f118cdaa7000000000000000000000000000000000000000000000000000000008152600401610e559190611c94565b60405180910390fd5b565b6000600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610f9757816040517f5b08ba18000000000000000000000000000000000000000000000000000000008152600401610f8e9190611c94565b60405180910390fd5b80600560008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31836040516110889190611b23565b60405180910390a3505050565b60008373ffffffffffffffffffffffffffffffffffffffff163b111561123f578273ffffffffffffffffffffffffffffffffffffffff1663150b7a02868685856040518563ffffffff1660e01b81526004016110f494939291906122e9565b6020604051808303816000875af192505050801561113057506040513d601f19601f8201168201806040525081019061112d919061234a565b60015b6111b4573d8060008114611160576040519150601f19603f3d011682016040523d82523d6000602084013e611165565b606091505b5060008151036111ac57836040517f64a0ae920000000000000000000000000000000000000000000000000000000081526004016111a39190611c94565b60405180910390fd5b805181602001fd5b63150b7a0260e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161461123d57836040517f64a0ae920000000000000000000000000000000000000000000000000000000081526004016112349190611c94565b60405180910390fd5b505b5050505050565b606060405180602001604052806000815250905090565b606061126882610ae0565b506000611273611246565b9050600081511161129357604051806020016040528060008152506112be565b8061129d84611670565b6040516020016112ae9291906121f9565b6040516020818303038152906040525b915050919050565b6112e082826040518060200160405280600081525061173e565b5050565b806006600084815260200190815260200160002090816113049190612523565b507ff8e1a15aba9398e019f0b49df1a4fde98ee17ae345cb5f6b5e2c27f5033e8ce7826040516113349190611daa565b60405180910390a15050565b60007f01ffc9a7000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916149050919050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80806114205750600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614155b1561155457600061143084610ae0565b9050600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415801561149b57508273ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614155b80156114ae57506114ac818461089d565b155b156114f057826040517fa9fbf51f0000000000000000000000000000000000000000000000000000000081526004016114e79190611c94565b60405180910390fd5b811561155257838573ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45b505b836004600085815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505050565b6115b7838383611762565b61166b57600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff160361162c57806040517f7e2732890000000000000000000000000000000000000000000000000000000081526004016116239190611daa565b60405180910390fd5b81816040517f177e802f0000000000000000000000000000000000000000000000000000000081526004016116629291906125f5565b60405180910390fd5b505050565b60606000600161167f84611823565b01905060008167ffffffffffffffff81111561169e5761169d611e3b565b5b6040519080825280601f01601f1916602001820160405280156116d05781602001600182028036833780820191505090505b509050600082602001820190505b600115611733578080600190039150507f3031323334353637383961626364656600000000000000000000000000000000600a86061a8153600a85816117275761172661261e565b5b049450600085036116de575b819350505050919050565b6117488383611976565b61175d611753610ba5565b6000858585611095565b505050565b60008073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415801561181a57508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff1614806117db57506117da848461089d565b5b8061181957508273ffffffffffffffffffffffffffffffffffffffff1661180183610b68565b73ffffffffffffffffffffffffffffffffffffffff16145b5b90509392505050565b600080600090507a184f03e93ff9f4daa797ed6e38ed64bf6a1f0100000000000000008310611881577a184f03e93ff9f4daa797ed6e38ed64bf6a1f01000000000000000083816118775761187661261e565b5b0492506040810190505b6d04ee2d6d415b85acef810000000083106118be576d04ee2d6d415b85acef810000000083816118b4576118b361261e565b5b0492506020810190505b662386f26fc1000083106118ed57662386f26fc1000083816118e3576118e261261e565b5b0492506010810190505b6305f5e1008310611916576305f5e100838161190c5761190b61261e565b5b0492506008810190505b612710831061193b5761271083816119315761193061261e565b5b0492506004810190505b6064831061195e57606483816119545761195361261e565b5b0492506002810190505b600a831061196d576001810190505b80915050919050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036119e85760006040517f64a0ae920000000000000000000000000000000000000000000000000000000081526004016119df9190611c94565b60405180910390fd5b60006119f683836000610bbf565b9050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614611a6a5760006040517f73c6ac6e000000000000000000000000000000000000000000000000000000008152600401611a619190611c94565b60405180910390fd5b505050565b6000604051905090565b600080fd5b600080fd5b60007fffffffff0000000000000000000000000000000000000000000000000000000082169050919050565b611ab881611a83565b8114611ac357600080fd5b50565b600081359050611ad581611aaf565b92915050565b600060208284031215611af157611af0611a79565b5b6000611aff84828501611ac6565b91505092915050565b60008115159050919050565b611b1d81611b08565b82525050565b6000602082019050611b386000830184611b14565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015611b78578082015181840152602081019050611b5d565b60008484015250505050565b6000601f19601f8301169050919050565b6000611ba082611b3e565b611baa8185611b49565b9350611bba818560208601611b5a565b611bc381611b84565b840191505092915050565b60006020820190508181036000830152611be88184611b95565b905092915050565b6000819050919050565b611c0381611bf0565b8114611c0e57600080fd5b50565b600081359050611c2081611bfa565b92915050565b600060208284031215611c3c57611c3b611a79565b5b6000611c4a84828501611c11565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000611c7e82611c53565b9050919050565b611c8e81611c73565b82525050565b6000602082019050611ca96000830184611c85565b92915050565b611cb881611c73565b8114611cc357600080fd5b50565b600081359050611cd581611caf565b92915050565b60008060408385031215611cf257611cf1611a79565b5b6000611d0085828601611cc6565b9250506020611d1185828601611c11565b9150509250929050565b600080600060608486031215611d3457611d33611a79565b5b6000611d4286828701611cc6565b9350506020611d5386828701611cc6565b9250506040611d6486828701611c11565b9150509250925092565b600060208284031215611d8457611d83611a79565b5b6000611d9284828501611cc6565b91505092915050565b611da481611bf0565b82525050565b6000602082019050611dbf6000830184611d9b565b92915050565b611dce81611b08565b8114611dd957600080fd5b50565b600081359050611deb81611dc5565b92915050565b60008060408385031215611e0857611e07611a79565b5b6000611e1685828601611cc6565b9250506020611e2785828601611ddc565b9150509250929050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b611e7382611b84565b810181811067ffffffffffffffff82111715611e9257611e91611e3b565b5b80604052505050565b6000611ea5611a6f565b9050611eb18282611e6a565b919050565b600067ffffffffffffffff821115611ed157611ed0611e3b565b5b611eda82611b84565b9050602081019050919050565b82818337600083830152505050565b6000611f09611f0484611eb6565b611e9b565b905082815260208101848484011115611f2557611f24611e36565b5b611f30848285611ee7565b509392505050565b600082601f830112611f4d57611f4c611e31565b5b8135611f5d848260208601611ef6565b91505092915050565b60008060008060808587031215611f8057611f7f611a79565b5b6000611f8e87828801611cc6565b9450506020611f9f87828801611cc6565b9350506040611fb087828801611c11565b925050606085013567ffffffffffffffff811115611fd157611fd0611a7e565b5b611fdd87828801611f38565b91505092959194509250565b6000806040838503121561200057611fff611a79565b5b600061200e85828601611cc6565b925050602061201f85828601611cc6565b9150509250929050565b600067ffffffffffffffff82111561204457612043611e3b565b5b61204d82611b84565b9050602081019050919050565b600061206d61206884612029565b611e9b565b90508281526020810184848401111561208957612088611e36565b5b612094848285611ee7565b509392505050565b600082601f8301126120b1576120b0611e31565b5b81356120c184826020860161205a565b91505092915050565b600080604083850312156120e1576120e0611a79565b5b60006120ef85828601611cc6565b925050602083013567ffffffffffffffff8111156121105761210f611a7e565b5b61211c8582860161209c565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061216d57607f821691505b6020821081036121805761217f612126565b5b50919050565b600060608201905061219b6000830186611c85565b6121a86020830185611d9b565b6121b56040830184611c85565b949350505050565b600081905092915050565b60006121d382611b3e565b6121dd81856121bd565b93506121ed818560208601611b5a565b80840191505092915050565b600061220582856121c8565b915061221182846121c8565b91508190509392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061225782611bf0565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82036122895761228861221d565b5b600182019050919050565b600081519050919050565b600082825260208201905092915050565b60006122bb82612294565b6122c5818561229f565b93506122d5818560208601611b5a565b6122de81611b84565b840191505092915050565b60006080820190506122fe6000830187611c85565b61230b6020830186611c85565b6123186040830185611d9b565b818103606083015261232a81846122b0565b905095945050505050565b60008151905061234481611aaf565b92915050565b6000602082840312156123605761235f611a79565b5b600061236e84828501612335565b91505092915050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026123d97fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8261239c565b6123e3868361239c565b95508019841693508086168417925050509392505050565b6000819050919050565b600061242061241b61241684611bf0565b6123fb565b611bf0565b9050919050565b6000819050919050565b61243a83612405565b61244e61244682612427565b8484546123a9565b825550505050565b600090565b612463612456565b61246e818484612431565b505050565b5b818110156124925761248760008261245b565b600181019050612474565b5050565b601f8211156124d7576124a881612377565b6124b18461238c565b810160208510156124c0578190505b6124d46124cc8561238c565b830182612473565b50505b505050565b600082821c905092915050565b60006124fa600019846008026124dc565b1980831691505092915050565b600061251383836124e9565b9150826002028217905092915050565b61252c82611b3e565b67ffffffffffffffff81111561254557612544611e3b565b5b61254f8254612155565b61255a828285612496565b600060209050601f83116001811461258d576000841561257b578287015190505b6125858582612507565b8655506125ed565b601f19841661259b86612377565b60005b828110156125c35784890151825560018201915060208501945060208101905061259e565b868310156125e057848901516125dc601f8916826124e9565b8355505b6001600288020188555050505b505050505050565b600060408201905061260a6000830185611c85565b6126176020830184611d9b565b9392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fdfea264697066735822122082e51db96cb39b90089e55f216baf1208a749f3abc6ced20bd030101bf5d7a1264736f6c634300081a0033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_MINTNFT = "mintNFT";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event BATCHMETADATAUPDATE_EVENT = new Event("BatchMetadataUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event METADATAUPDATE_EVENT = new Event("MetadataUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected NFTContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFTContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFTContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFTContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mintNFT(String recipient, String tokenURI) {
        final Function function = new Function(
                FUNC_MINTNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.Utf8String(tokenURI)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<BatchMetadataUpdateEventResponse> getBatchMetadataUpdateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BATCHMETADATAUPDATE_EVENT, transactionReceipt);
        ArrayList<BatchMetadataUpdateEventResponse> responses = new ArrayList<BatchMetadataUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BatchMetadataUpdateEventResponse typedResponse = new BatchMetadataUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._fromTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._toTokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BatchMetadataUpdateEventResponse> batchMetadataUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, BatchMetadataUpdateEventResponse>() {
            @Override
            public BatchMetadataUpdateEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BATCHMETADATAUPDATE_EVENT, log);
                BatchMetadataUpdateEventResponse typedResponse = new BatchMetadataUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._fromTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._toTokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BatchMetadataUpdateEventResponse> batchMetadataUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BATCHMETADATAUPDATE_EVENT));
        return batchMetadataUpdateEventFlowable(filter);
    }

    public List<MetadataUpdateEventResponse> getMetadataUpdateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(METADATAUPDATE_EVENT, transactionReceipt);
        ArrayList<MetadataUpdateEventResponse> responses = new ArrayList<MetadataUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MetadataUpdateEventResponse typedResponse = new MetadataUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MetadataUpdateEventResponse> metadataUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, MetadataUpdateEventResponse>() {
            @Override
            public MetadataUpdateEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(METADATAUPDATE_EVENT, log);
                MetadataUpdateEventResponse typedResponse = new MetadataUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MetadataUpdateEventResponse> metadataUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(METADATAUPDATE_EVENT));
        return metadataUpdateEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final Function function = new Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] data) {
        final Function function = new Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final Function function = new Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final Function function = new Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final Function function = new Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final Function function = new Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static NFTContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFTContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFTContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFTContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFTContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFTContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFTContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NFTContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class BatchMetadataUpdateEventResponse extends BaseEventResponse {
        public BigInteger _fromTokenId;

        public BigInteger _toTokenId;
    }

    public static class MetadataUpdateEventResponse extends BaseEventResponse {
        public BigInteger _tokenId;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
