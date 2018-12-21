package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.yuna.model.BillCalendar;
import com.protean.legislativetracker.yuna.model.BillHistory;
import com.protean.legislativetracker.yuna.model.BillProgress;
import com.protean.legislativetracker.yuna.model.BillSponsor;
import com.protean.legislativetracker.yuna.model.BillText;
import com.protean.legislativetracker.yuna.model.RollCall;
import com.protean.legislativetracker.yuna.model.Subject;
import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LegiscanModelMapperSingleItemTest {

    private com.protean.legislativetracker.yuna.model.Bill bill;
    private DateFormat dtf;
    private HttpRequestService httpRequestService;

    @Before
    public void setUp() {
        this.httpRequestService = new HttpRequestServiceImpl();
        Bill bill = httpRequestService.getPojoFromJson(
                Bill.class,
                JsonFileLoader.readJsonFileAsString(
                        "src/test/resources/get_bill_expected_response.json"),
                "bill");
        this.bill = BillModelMapper.legiscanBillToModelBill(bill);
        dtf = new SimpleDateFormat("yyyy-MM-dd");
        dtf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void billModelToLegiscan_Bill_IdMapped() {
        assertEquals(897860, bill.getBillId().longValue());
    }

    @Test
    public void billModelToLegiscan_Bill_StateIdMapped() {
        assertEquals(19, bill.getState().getStateId().intValue());
    }

    @Test
    public void billModelToLegiscan_Bill_StateNameMapped() {
        assertEquals("ME", bill.getState().getStateName());
    }

    @Test
    public void billModelToLegiscan_BillSession_SessionMapped() {
        assertEquals(1258, bill.getLegislativeSession().getSessionId().intValue());
    }

    @Test
    public void billModelToLegiscan_BillSession_SessionNameMapped() {
        assertEquals("128th Legislature", bill.getLegislativeSession().getSessionName());
    }

    @Test
    public void billModelToLegiscan_BillSession_SessionTitleMapped() {
        assertEquals("128th Legislature", bill.getLegislativeSession().getSessionTitle());
    }

    @Test
    public void billModelToLegiscan_BillSession_SessionYearStartMapped() {
        assertEquals(2017, bill.getLegislativeSession().getYearStart().intValue());
    }

    @Test
    public void billModelToLegiscan_BillSession_SessionYearEndMapped() {
        assertEquals(2018, bill.getLegislativeSession().getYearEnd().intValue());
    }

    @Test
    public void billModelToLegiscan_BillBody_BodyIdMapped() {
        assertEquals(47, bill.getBody().getId().intValue());
    }


    @Test
    public void billModelToLegiscan_BillBody_BodyNameShortMapped() {
        assertEquals("H", bill.getBody().getBodyShort());
    }

    @Test
    public void billModelToLegiscan_BillBody_CurrentBodyIdMapped() {
        assertEquals(47, bill.getCurrentBody().getId().intValue());
    }

    @Test
    public void billModelToLegiscan_BillBody_CurrentBodyNameShortMapped() {
        assertEquals("H", bill.getCurrentBody().getBodyShort());
    }

    @Test
    public void billModelToLegiscan_BillType_BillTypeIDMapped() {
        assertEquals(1, bill.getType().getId().intValue());
    }

    @Test
    public void billModelToLegiscan_BillType_BillTypeAbbrMapped() {
        assertEquals("B", bill.getType().getBillTypeAbbreviation());
    }

    @Test
    public void billModelToLegiscan_BillType_BillTypeNameNull() {
        assertNull(bill.getType().getBillTypeName());
    }

    @Test
    public void billModelToLegiscan_Bill_BillNumberMapped() {
        assertEquals("LD8", bill.getBillNumber());
    }

    @Test
    public void billModelToLegiscan_BillStatus_StatusMapped() {
        assertEquals(4, bill.getStatus().getProgressId().intValue());
    }

    @Test
    public void billModelToLegiscan_BillStatus_StatusDateMapped() {
        assertEquals("2018-06-21", dtf.format(bill.getStatusDate()));
    }

    @Test
    public void billModelToLegiscan_BillUrl_StateLinkMapped() throws MalformedURLException {
        assertEquals(new URL("http://legislature.maine.gov/legis/bills/display_ps.asp?LD=8&snum=128"), bill.getStateUrl());
    }

    @Test
    public void billModelToLegiscan_BillUrl_LegiscanUrlMapped() throws MalformedURLException {
        assertEquals(new URL("https://legiscan.com/ME/bill/LD8/2017"), bill.getLegiscanUrl());
    }

    @Test
    public void billModelToLegiscan_BillChangeHash_ChangeHashMapped() {
        assertEquals("bb7e39c971b4c446b3a0fc3fc4913113", bill.getChangeHash());
    }

    @Test
    public void billModelToLegiscan_BillTitle_BillTitleMapped() {
        assertEquals("An Act To Provide Training for Forest Rangers To Carry Firearms", bill.getTitle());
    }

    @Test
    public void billModelToLegiscan_BillDescription_DescriptionMapped() {
        assertEquals("An Act To Provide Training for Forest Rangers To Carry Firearms", bill.getDescription());
    }

    @Test
    public void billModelToLegiscan_BillPendingCommitteeId_PendingCommitteeIdMapped() {
        assertNull(bill.getCommittee());
    }

    @Test
    public void billModelToLegiscan_BillProgress_ProgressMapped() {
        Set<BillProgress> billProgressSet = bill.getProgress();
        boolean progressOne = false;
        boolean progressTwo = false;
        boolean progressThree = false;
        boolean progressFour = false;
        boolean progressFive = false;
        boolean progressSix = false;
        boolean progressSeven = false;

        for (BillProgress p : billProgressSet) {
            if (dtf.format(p.getProgressDate()).equals("2017-01-05")
                    && p.getProgress().getProgressId() == 1
                    && p.getId().getProgressStep() >= 1) progressOne = true;
            if (dtf.format(p.getProgressDate()).equals("2017-01-05")
                    && p.getProgress().getProgressId() == 9
                    && p.getId().getProgressStep() >= 1) progressTwo = true;
            if (dtf.format(p.getProgressDate()).equals("2017-01-10")
                    && p.getProgress().getProgressId() == 9
                    && p.getId().getProgressStep() >= 1) progressThree = true;
            if (dtf.format(p.getProgressDate()).equals("2017-06-08")
                    && p.getProgress().getProgressId() == 2
                    && p.getId().getProgressStep() >= 1) progressFour = true;
            if (dtf.format(p.getProgressDate()).equals("2017-06-09")
                    && p.getProgress().getProgressId() == 3
                    && p.getId().getProgressStep() >= 1) progressFive = true;
            if (dtf.format(p.getProgressDate()).equals("2018-06-21")
                    && p.getProgress().getProgressId() == 4
                    && p.getId().getProgressStep() >= 1) progressSix = true;
            if (dtf.format(p.getProgressDate()).equals("2018-07-09")
                    && p.getProgress().getProgressId() == 7
                    && p.getId().getProgressStep() >= 1) progressSeven = true;
        }
        assertTrue(progressOne);
        assertTrue(progressTwo);
        assertTrue(progressThree);
        assertTrue(progressFour);
        assertTrue(progressFive);
        assertTrue(progressSix);
        assertTrue(progressSeven);
    }

    @Test
    public void billModelToLegiscan_BillHistory_SetHasAllElements() {
        assertEquals(44, bill.getHistories().size());
    }

    @Test
    public void billModelToLegiscan_BillHistory_HistoryMapped() {
        Set<BillHistory> billProgressSet = bill.getHistories();
        boolean progressFirst = false;
        boolean progressMiddle = false;
        boolean progressLast = false;

        for (BillHistory p : billProgressSet) {
            if (dtf.format(p.getDate()).equals("2017-01-05")
                    && p.getAction().equals("Committee on Agriculture, Conservation and Forestry suggested and ordered printed.")
                    && p.getBody().getBodyShort().equals("H")
                    && p.getBody().getId() == 47
                    && p.getHistoryMajor() == 0) progressFirst = true;
            if (dtf.format(p.getDate()).equals("2017-06-12")
                    && p.getAction().equals("Sent for concurrence. ORDERED SENT FORTHWITH.")
                    && p.getBody().getBodyShort().equals("H")
                    && p.getBody().getId() == 47
                    && p.getHistoryMajor() == 0) progressMiddle = true;
            if (dtf.format(p.getDate()).equals("2018-07-09")
                    && p.getAction().equals("This Bill, having been returned by the Governor, together with objections to the same pursuant to Article IV, Part Third, Section 2 of the Constitution of the State of Maine, after reconsideration, the House proceeded to vote on the question: \"Shall this Bill become a law notwithstanding the objections of the Governor?\"")
                    && p.getBody().getBodyShort().equals("H")
                    && p.getBody().getId() == 47
                    && p.getHistoryMajor() == 0) progressLast = true;
        }
        assertTrue(progressFirst);
        assertTrue(progressMiddle);
        assertTrue(progressLast);
    }

    @Test
    public void billModelToLegiscan_BillSponsors_SetHasAllElements() {
        assertEquals(10, bill.getSponsors().size());
    }

    @Test
    public void billModelToLegiscan_BillSponsors_SponsorsMapped() {
        Set<BillSponsor> billProgressSet = bill.getSponsors();
        boolean sponsorFound = false;
        for (BillSponsor p : billProgressSet) {
            if (p.getPerson().getId() == 16786) {
                assertEquals("t0g0kg1p", p.getPerson().getPersonHash());
                assertEquals(2, p.getPerson().getParty().getId().intValue());
                assertEquals('R', p.getPerson().getParty().getAbbreviation().charValue());
                assertEquals(1, p.getPerson().getRole().getId().intValue());
                assertEquals("Rep", p.getPerson().getRole().getRoleAbbreviation());
                assertEquals("William Tuell", p.getPerson().getName());
                assertEquals("William", p.getPerson().getFirstName());
                assertEquals("R.", p.getPerson().getMiddleName());
                assertEquals("Tuell", p.getPerson().getLastName());
                assertEquals("", p.getPerson().getSuffix());
                assertEquals("", p.getPerson().getNickname());
                assertEquals("HD-139", p.getPerson().getDistrict());
                assertEquals(23424445, p.getPerson().getFollowTheMoneyId().longValue());
                assertEquals(149293, p.getPerson().getVoteSmartId().longValue());
                assertNull(p.getPerson().getOpenSecretsId());
                assertEquals("William_Tuell", p.getPerson().getBallotpedia());
                assertEquals(1, p.getSponsorType().getId().intValue());
                assertEquals(1, p.getId().getSponsorOrder().intValue());
                sponsorFound = true;
            }
        }
        assertTrue(sponsorFound);
    }

    @Test
    public void billModelToLegiscan_BillSubject_SubjectMapped() {
        Set<Subject> subjectSet = bill.getSubjects();
        boolean subjectOneFound = false;
        boolean subjectTwoFound = false;
        for (Subject s : subjectSet) {
            if (s.getId() == 13012) {
                assertEquals("Forestry", s.getName());
                subjectOneFound = true;
            }
            if (s.getId() == 22912) {
                assertEquals("Forest Rangers", s.getName());
                subjectTwoFound = true;
            }
        }
        assertTrue(subjectOneFound);
        assertTrue(subjectTwoFound);
    }

    @Test
    public void billModelToLegiscan_BillText_TextsMapped() throws MalformedURLException {
        Set<BillText> texts = bill.getTexts();
        boolean textOneFound = false;
        boolean textTwoFound = false;
        boolean textThreeFound = false;
        boolean textFourFound = false;
        for (BillText t : texts) {
            if (t.getTextId() == 1444527) {
//                assertEquals("0000-00-00", dtf.format(t.getDate()));
                assertEquals("Introduced", t.getTextType().getName());
                assertEquals(1, t.getTextType().getId().intValue());
                assertEquals("application/pdf", t.getMimeType().getType());
                assertEquals(2, t.getMimeType().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/text/LD8/id/1444527"), t.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/legis/bills/getPDF.asp?paper=HP0009&item=1&snum=128"), t.getStateUrl());
                textOneFound = true;
            }
            if (t.getTextId() == 1631792) {
//                assertEquals("0000-00-00", dtf.format(t.getDate()));
                assertEquals("Amended", t.getTextType().getName());
                assertEquals(3, t.getTextType().getId().intValue());
                assertEquals("application/pdf", t.getMimeType().getType());
                assertEquals(2, t.getMimeType().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/text/LD8/id/1631792"), t.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/legis/bills/getPDF.asp?paper=HP0009&item=2&snum=128"), t.getStateUrl());
                textTwoFound = true;
            }
            if (t.getTextId() == 1810398) {
//                assertEquals("0000-00-00", dtf.format(t.getDate()));
                assertEquals("Amended", t.getTextType().getName());
                assertEquals(3, t.getTextType().getId().intValue());
                assertEquals("application/pdf", t.getMimeType().getType());
                assertEquals(2, t.getMimeType().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/text/LD8/id/1810398"), t.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/legis/bills/getPDF.asp?paper=HP0009&item=4&snum=128"), t.getStateUrl());
                textThreeFound = true;
            }
            if (t.getTextId() == 1813238) {
//                assertEquals("0000-00-00", dtf.format(t.getDate()));
                assertEquals("Chaptered", t.getTextType().getName());
                assertEquals(6, t.getTextType().getId().intValue());
                assertEquals("application/pdf", t.getMimeType().getType());
                assertEquals(2, t.getMimeType().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/text/LD8/id/1813238"), t.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/legis/bills/getPDF.asp?paper=HP0009&item=5&snum=128"), t.getStateUrl());
                textFourFound = true;
            }
        }
        assertTrue(textOneFound);
        assertTrue(textTwoFound);
        assertTrue(textThreeFound);
        assertTrue(textFourFound);
    }

    @Test
    public void billModelToLegiscan_BillVote_VotesMapped() throws MalformedURLException {
        Set<RollCall> votes = bill.getRollCalls();
        boolean voteOneFound = false;
        boolean voteTwoFound = false;
        boolean voteThreeFound = false;
        boolean voteFourFound = false;
        boolean voteFiveFound = false;
        for (RollCall v : votes) {
            if (v.getVoteId() == 661120) {
                assertEquals("2017-06-08", dtf.format(v.getDate()));
                assertEquals("Acc Maj Otp As Amended Rep RC #274", v.getDescription());
                assertEquals(132, v.getYea().intValue());
                assertEquals(15, v.getNay().intValue());
                assertEquals(0, v.getNv().intValue());
                assertEquals(4, v.getAbsent().intValue());
                assertEquals(151, v.getTotal().intValue());
                assertTrue(v.getPassed());
                assertEquals('H', v.getBody().getAbbreviation().charValue());
                assertEquals(47, v.getBody().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/rollcall/LD8/id/661120"), v.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/LawMakerWeb/rollcall.asp?ID=280062419&chamber=House&serialnumber=274"), v.getStateUrl());
                voteOneFound = true;
            }
            if (v.getVoteId() == 766566) {
                assertEquals("2018-06-21", dtf.format(v.getDate()));
                assertEquals("Recede And Concur RC #681", v.getDescription());
                assertEquals(118, v.getYea().intValue());
                assertEquals(17, v.getNay().intValue());
                assertEquals(0, v.getNv().intValue());
                assertEquals(16, v.getAbsent().intValue());
                assertEquals(151, v.getTotal().intValue());
                assertTrue(v.getPassed());
                assertEquals('H', v.getBody().getAbbreviation().charValue());
                assertEquals(47, v.getBody().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/rollcall/LD8/id/766566"), v.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/LawMakerWeb/rollcall.asp?ID=280062419&chamber=House&serialnumber=681"), v.getStateUrl());
                voteTwoFound = true;
            }
            if (v.getVoteId() == 766567) {
                assertEquals("2018-06-21", dtf.format(v.getDate()));
                assertEquals("Enactment RC #698", v.getDescription());
                assertEquals(30, v.getYea().intValue());
                assertEquals(1, v.getNay().intValue());
                assertEquals(0, v.getNv().intValue());
                assertEquals(4, v.getAbsent().intValue());
                assertEquals(35, v.getTotal().intValue());
                assertTrue(v.getPassed());
                assertEquals('S', v.getBody().getAbbreviation().charValue());
                assertEquals(48, v.getBody().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/rollcall/LD8/id/766567"), v.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/LawMakerWeb/rollcall.asp?ID=280062419&chamber=Senate&serialnumber=698"), v.getStateUrl());
                voteThreeFound = true;
            }
            if (v.getVoteId() == 769854) {
                assertEquals("2018-07-09", dtf.format(v.getDate()));
                assertEquals("Reconsideration - Veto RC #715", v.getDescription());
                assertEquals(133, v.getYea().intValue());
                assertEquals(9, v.getNay().intValue());
                assertEquals(0, v.getNv().intValue());
                assertEquals(9, v.getAbsent().intValue());
                assertEquals(151, v.getTotal().intValue());
                assertTrue(v.getPassed());
                assertEquals('H', v.getBody().getAbbreviation().charValue());
                assertEquals(47, v.getBody().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/rollcall/LD8/id/769854"), v.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/LawMakerWeb/rollcall.asp?ID=280062419&chamber=House&serialnumber=715"), v.getStateUrl());
                voteFourFound = true;
            }
            if (v.getVoteId() == 769855) {
                assertEquals("2018-07-09", dtf.format(v.getDate()));
                assertEquals("Reconsideration - Veto RC #727", v.getDescription());
                assertEquals(29, v.getYea().intValue());
                assertEquals(4, v.getNay().intValue());
                assertEquals(0, v.getNv().intValue());
                assertEquals(2, v.getAbsent().intValue());
                assertEquals(35, v.getTotal().intValue());
                assertTrue(v.getPassed());
                assertEquals('S', v.getBody().getAbbreviation().charValue());
                assertEquals(48, v.getBody().getId().intValue());
                assertEquals(new URL("https://legiscan.com/ME/rollcall/LD8/id/769855"), v.getLegiscanUrl());
                assertEquals(new URL("http://legislature.maine.gov/LawMakerWeb/rollcall.asp?ID=280062419&chamber=Senate&serialnumber=727"), v.getStateUrl());
                voteFiveFound = true;
            }
        }
        assertTrue(voteOneFound);
        assertTrue(voteTwoFound);
        assertTrue(voteThreeFound);
        assertTrue(voteFourFound);
        assertTrue(voteFiveFound);
    }

    @Test
    public void billModelToLegiscan_BillCalendar_CalendarsMapped() {
        Set<BillCalendar> calendars = bill.getCalendars();
        boolean voteOneFound = false;
        boolean voteTwoFound = false;
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        for (BillCalendar c : calendars) {
            if (dtf.format(c.getDate()).equals("2017-01-31")) {
                assertEquals(897860, c.getBill().getBillId().longValue());
                assertEquals("Hearing", c.getEventType().getDescription());
                assertEquals("13:30", fmt.format(c.getTime()));
                assertEquals("Cross Building, Room 214", c.getLocation());
                assertEquals("Hearing", c.getDescription());
                voteOneFound = true;
            }
            if (dtf.format(c.getDate()).equals("2017-02-07")) {
//                assertEquals(new Long(897860), c.getSessionId().getByEntity().getBillId());
                assertEquals("Hearing", c.getEventType().getDescription());
                assertEquals("13:15", fmt.format(c.getTime()));
                assertEquals("Cross Building, Room 214", c.getLocation());
                assertEquals("Hearing", c.getDescription());
                voteTwoFound = true;
            }
        }
        assertTrue(voteOneFound);
        assertTrue(voteTwoFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modelToLegiscan_NullValueToMap_ThrowsIllegalArgumentException() {
        LegiscanModelMapper.modelToLegiscan(null, com.protean.legislativetracker.yuna.model.Bill.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modelToLegiscan_NullDestination_ThrowsIllegalArgumentException() {
        LegiscanModelMapper.modelToLegiscan(new Bill(),null);
    }
}