package test;



import java.lang.annotation.ElementType;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Math.max;


class Solution {



    public String Dudge(String s1 ,String s2){
        int c[][]=new int[1001][1001];
        for(int i = 0 ; i<s1.length();i++){
            for(int j = 0 ;j<s2.length();j++){
                if(i==0||j==0){
                    if(s1.charAt(i)==s2.charAt(j)) c[i][j]=1;
                    else c[i][j]=0;
                }
                else {
                    if(s1.charAt(i)==s2.charAt(j)) c[i][j]=c[i-1][j-1]+1;
                    else c[i][j]=0;
                }
            }
        }
        int flag=1;
        int ans=0,ansi=0,ansj=0;
        for(int i = 0;i<s1.length();i++){
            for(int j = 0 ; j <s2.length();j++){
                flag=1;
                if(c[i][j]>ans){
                    for(int k=0;k<c[i][j]/2;k++){
                        if(s1.charAt(i-k)!=s1.charAt(i-c[i][j]+1+k)) {flag=0;break;}
                    }
                    if(flag==1){
                        ansi=i;ansj=j;ans=c[i][j];
                    }
                }
            }
        }
        if(ans==0) return ""+s1.charAt(0);
        return s1.substring(ansi-ans+1,ansi+1);
    }
    public String longestPalindrome(String s) {
        if(s.length()==0) return "";
        StringBuffer sb=new StringBuffer(s);
        String s1 = sb.reverse().toString();
        return Dudge(s,s1);
    }
}

public class LeetCode {


    public static void main(String[] args) {
        String s="ccd";
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome(s));

    }

}
